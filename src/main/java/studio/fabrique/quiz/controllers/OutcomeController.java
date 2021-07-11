package studio.fabrique.quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import studio.fabrique.quiz.entities.Outcome;
import studio.fabrique.quiz.entities.QuestionAnswer;
import studio.fabrique.quiz.entities.User;
import studio.fabrique.quiz.services.OutcomeMakerService;
import studio.fabrique.quiz.services.OutcomeService;
import studio.fabrique.quiz.services.QuestionAnswerService;
import studio.fabrique.quiz.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/outcome")
public class OutcomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private OutcomeService outcomeService;

    @Autowired
    private OutcomeMakerService outcomeMakerService;

    @Autowired
    private QuestionAnswerService questionAnswerService;

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
        Outcome outcome = outcomeService.makeOutcome(outcomeMakerService.getCurrentMaker(httpServletRequest.getSession()),user);
        outcome = outcomeService.saveOutcome(outcome);
        outcomeMakerService.resetCurrentMaker(httpServletRequest.getSession());
        model.addAttribute("outcome",outcome);
        return "outcome_result";
    }

    @GetMapping("/show")
    public String showOutcome(Model model,
                              Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        User user = userService.findByUserName(principal.getName());
        List<Outcome> outcomes = outcomeService.findByUser(user);
        List<QuestionAnswer> questionAnswers = questionAnswerService.getAllQuestionAnswersByOutcome(outcomes);
        model.addAttribute("questionsAnswers",questionAnswers);
        return "outcome_personal";
    }

}
