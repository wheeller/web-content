package hello.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

import hello.repos.MessageRepo;
import hello.domain.Message;


@Controller
public class MainController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String root() {
        logger.info("Get into /");
        return "main";
    }

    @GetMapping("/test")
    public String test() {
        logger.info("Get into /test");
        return "test";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, Map<String, Object> model) {
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("MainController: Empty parameters in login form");
            System.out.println("MainController: login failed");
            return "login";
        }
        model.put("username", username);
        model.put("password", password);
        System.out.println("MainController: login success");
        return "login";
    }


    @GetMapping("/message")
    public String message(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "message";
    }

    @PostMapping("add")
    public String addMessage(@RequestParam(name = "text") String text, @RequestParam(name = "tag") String tag, Map<String, Object> model) {
        if (text.isEmpty() || tag.isEmpty())
            System.out.println("Empty parameters in add form");
        else {
            Message message = new Message(text, tag);
            messageRepo.save(message);
        }
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "message";
    }

    @PostMapping("filter")
    public String filter(@RequestParam(name = "tag") String tag, Map<String, Object> model) {
        Iterable<Message> messages;

        if (tag != null && !tag.isEmpty())
            messages = messageRepo.findByTag(tag);
        else
            messages = messageRepo.findAll();

        model.put("messages", messages);
        return "message";
    }
}