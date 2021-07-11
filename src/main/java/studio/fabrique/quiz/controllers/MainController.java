package studio.fabrique.quiz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    //http://localhost:8189/app/
    @GetMapping("/")
    public String getIndexPage(Model model){ return "index"; }
}
