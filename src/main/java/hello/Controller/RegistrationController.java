package hello.Controller;

import hello.domain.User;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping ("/registration")
    String newUser(User user, Map<String, Object> model) {
        if (!userService.addUser(user)) {
        model.put("message", "User exists!");
        return "registration";
        }
        model.put("message", "User successfully added!");
        return "redirect:/";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);

        if(isActivated){
            model.addAttribute("message", "Acivation succesed!");
        }
        else {
            model.addAttribute("message", "Acivation code not found.");
        }
        return "main";
    }
}
