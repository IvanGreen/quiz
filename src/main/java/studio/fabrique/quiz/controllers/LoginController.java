package studio.fabrique.quiz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    //http://localhost:8189/app/login
    @GetMapping("/login")
    public String showMyLoginPage() { return "login"; }

    @GetMapping("/accessDenied")
    public String showAccessDeniedPage() { return "access-denied"; }
}
