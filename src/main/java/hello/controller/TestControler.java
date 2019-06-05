package hello.controller;

import hello.service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestControler {
    @Autowired
    MailSender mailSender;

    @GetMapping("/NPE")
    public void testNPE() throws NullPointerException{
//        mailSender.send("natasuriz@hotelnextmail.com", "Hello test", "Body language");
        throw new NullPointerException("It's a test NPE");
    }

    @GetMapping("/NFE")
    public void testNFE() throws NumberFormatException{
        throw new NumberFormatException("It's a test NFE");
    }

    @GetMapping("/IE")
    public void testIE() throws Exception{
        throw new Exception("It's a test IE");
    }

}
