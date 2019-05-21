//package hello.controller;
//
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class ExeptionController {
//    @ExceptionHandler(BadCredentialsException.class)
//    public String handleBadCredentialsException(Model model) {
//
//        model.addAttribute("message", "User Not Found");
//        return "main";
//    }
//}
