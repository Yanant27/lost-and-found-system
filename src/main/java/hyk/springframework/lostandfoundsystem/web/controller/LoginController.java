package hyk.springframework.lostandfoundsystem.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Htoo Yanant Khin
 **/
@Slf4j
@Controller
public class LoginController {
    // Login form
    @GetMapping("/login")
    public String login() {
        log.debug("Login Controller - Show login page");
        return "login";
    }

//    @GetMapping("/login")
//    public String login(@AuthenticationPrincipal UserDetails userDetails) {
//        if (userDetails == null) {
//            return "login";
//        } else {
//            return "redirect:/index";
//        }
//    }
}
