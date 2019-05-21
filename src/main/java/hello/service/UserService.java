package hello.service;

import hello.domain.Role;
import hello.domain.User;
import hello.repos.MessageRepo;
import hello.repos.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    static final Logger uLogger = LogManager.getLogger(UserService.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            uLogger.warn("User not found by user name");
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            uLogger.debug("User found in DB!");
            return false;
        }

        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));

        user.setActivationCode(UUID.randomUUID().toString());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

        sendMessage(user);

        return true;
    }

    public boolean deleteUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb == null) {
            uLogger.debug("User not found in DB!");
            return false;
        }

        if (!messageRepo.findByAuthorId(user.getId()).isEmpty()){
            messageRepo.deleteAll(messageRepo.findByAuthorId(user.getId()));
            uLogger.debug(user.getUsername() + "'s messages deleted!");
        }

        userRepo.delete(user);
        uLogger.debug("Delete user:" + user.getUsername());
        return true;
    }

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s!\n" +
                            "Welcome to Web-Content learning project!\n" +
                            "Please, visit http://localhost:8888/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            uLogger.debug("send email: " + message);
            mailSender.send(user.getEmail(), "Activation code", message);
        }
    }


    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);
    }


    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null) {
            uLogger.warn("User not found by activation code");
            return false;
        }

        user.setActivationCode(null);
        user.setActive(true);
        userRepo.save(user);

        uLogger.debug("User activated");
        return true;
    }

    public void updateProfile(User user, String password, String email) {

        String userEmail = user.getEmail();

        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));

        if (isEmailChanged) {
            uLogger.debug("User email set");
            user.setEmail(email);
        }

        if (!StringUtils.isEmpty(email)) {
            String activationCode = UUID.randomUUID().toString();
            user.setActivationCode(activationCode);
        }
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(passwordEncoder.encode(password));
        }
        if(isEmailChanged) {
            sendMessage(user);
        }

        uLogger.debug("User updated");
        userRepo.save(user);
    }


}