package studio.fabrique.quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import studio.fabrique.quiz.entities.Answer;
import studio.fabrique.quiz.entities.Question;
import studio.fabrique.quiz.entities.QuestionAnswer;
import studio.fabrique.quiz.services.AnswerService;
import studio.fabrique.quiz.services.OutcomeMakerService;
import studio.fabrique.quiz.services.QuestionService;
import studio.fabrique.quiz.utils.OutcomeMaker;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AnsweredController {

    @Autowired
    private OutcomeMakerService outcomeMakerService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    //вот тут падаю с ошибкой
    @GetMapping("/show")
    public String getAnsweredQuestion(Model model, HttpSession httpSession) {
        OutcomeMaker outcomeMaker = outcomeMakerService.getCurrentMaker(httpSession);
        for (QuestionAnswer q : outcomeMaker.getQuestionAnswers()) {
            System.out.println(q.getQuestion());
            System.out.println(q.getAnswer());
        }
        List<Question> questionList = questionService.getAllQuestions();
        List<Answer> answers = answerService.getAllAnswers();
        model.addAttribute("outcomeMaker",outcomeMaker);
        model.addAttribute("questionList",questionList);
        model.addAttribute("answers",answers);
        return "show";
    }

}
