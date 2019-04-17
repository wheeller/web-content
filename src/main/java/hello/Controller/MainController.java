package hello.Controller;

import hello.domain.Message;
import hello.domain.User;
import hello.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/message")
    public String message(@RequestParam(required = false, defaultValue = "") String filter, Model model) {

        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty())
            messages = messageRepo.findByTag(filter);
        else
            messages = messageRepo.findAll();

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "message";
    }


    @PostMapping("add")
    public String addMessage(
            @AuthenticationPrincipal User user,
            @RequestParam(name = "text") String text,
            @RequestParam(name = "tag") String tag,
            @RequestParam(name="file") MultipartFile file,
            Map<String, Object> model) throws IOException {

        if (text.isEmpty() || tag.isEmpty())
            System.out.println("Empty parameters in add form");
        else {
            Message message = new Message(text, tag, user);
            if (file != null && ! file.getOriginalFilename().isEmpty()){
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()){
                    uploadDir.mkdir();
                }

                String resultFileName = UUID.randomUUID().toString() + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" +   resultFileName));
                message.setFilename(resultFileName);
            }
            messageRepo.save(message);
        }

        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        model.put("filter", "");
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
