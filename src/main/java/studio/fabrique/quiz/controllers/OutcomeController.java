package studio.fabrique.quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import studio.fabrique.quiz.entities.Outcome;
import studio.fabrique.quiz.entities.User;
import studio.fabrique.quiz.services.OutcomeMakerService;
import studio.fabrique.quiz.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class OutcomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private OutcomeMakerService outcomeMakerService;

    @GetMapping("/confirm")
    public String confirm(Model model,
                          HttpServletRequest httpServletRequest,
                          Principal principal){
        User user;
        if (principal == null) {
            user = userService.findByUserName("Incognita");
        } else {
            user = userService.findByUserName(principal.getName());
        }
//        Outcome outcome =
        return "index";
    }
}
