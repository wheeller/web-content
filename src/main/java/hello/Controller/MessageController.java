package hello.Controller;

import hello.domain.Message;
import hello.domain.User;
import hello.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class MessageController {

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
            @Valid Message message,
            BindingResult bindingResult,
            Model model,
            @RequestParam(name = "file") MultipartFile file
    ) throws IOException {

        message.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);
        } else {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String resultFileName = UUID.randomUUID().toString() + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFileName));
                message.setFilename(resultFileName);
            }
            model.addAttribute("message", null);
            messageRepo.save(message);
        }

        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        model.addAttribute("filter", "");
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
