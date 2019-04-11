package hello.Controller;

import hello.domain.Role;
import hello.domain.User;
import hello.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping ("/registration")
    String newUser(User user, Map<String, Object> model) {
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActivity(true);
        user.setRoles(Collections.singleton(Role.USER));

        String encoded=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encoded);

        userRepo.save(user);
        System.out.println("RegistrationController: user " + user.toString() + " added to DB");
        return "redirect:/login";
    }
}
