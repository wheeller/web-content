package hello.controller;

import hello.domain.User;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {

//    final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    String newUser(
            @RequestParam("password2") String passwordConfirm,
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {

        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);

        if (isConfirmEmpty) {
            model.addAttribute("password2Error", "Confirmation  cannot be empty");
        }

        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Password are different");
            return "registration";
        }

        if (isConfirmEmpty || bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        model.addAttribute("messageType", "success");
        model.addAttribute("message", "User successfully added\nFollow instructions in email to activate");
        return "redirect:/";
    }

    @GetMapping("/activate/{code}")
    public String activate(
            Model model,
            @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "Acivation succesed! Please log in");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Acivation code not found");
        }
        return "redirect:/";
    }
}
