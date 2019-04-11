package hello.Controller;

import hello.domain.Message;
import hello.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/message")
    public String message(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "message";
    }

    @PostMapping("add")
    public String addMessage(
//            @AuthenticationPrincipal User user,
            @RequestParam(name = "text") String text,
            @RequestParam(name = "tag") String tag,
            Map<String, Object> model) {

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
