package studio.fabrique.quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import studio.fabrique.quiz.entities.Question;
import studio.fabrique.quiz.entities.Quiz;
import studio.fabrique.quiz.entities.User;
import studio.fabrique.quiz.entities.UserAnswer;
import studio.fabrique.quiz.services.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuizQuestionService quizQuestionService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAnswerService userAnswerService;

    @Autowired
    private AnsweredUsersService answeredUsersService;

    @GetMapping("/showQuzzes")
    public String showQuzzes(Model model){
        List<Quiz> quizzes = quizService.getAllConfirmedQuiz();
        model.addAttribute("quizzes",quizzes);
        return "quizzes_page";
    }

    @GetMapping("/start/{id}")
    public String startQuiz(Model model,
                            @PathVariable("id") Long id){
        Quiz quiz = quizService.findById(id);
        List<Question> questionList = quizQuestionService.getQuestionsByQuizId(id);
        model.addAttribute("quiz",quiz);
        model.addAttribute("questionList",questionList);
        return "quiz_start";
    }

    @GetMapping("question/answer/{id}")
    public String questionAnswer(Model model,
                                 @PathVariable("id") Long id) {
        Question question = questionService.getOneById(id);
        UserAnswer userAnswer = new UserAnswer();
        model.addAttribute("userAnswer",userAnswer);
        model.addAttribute("question",question);
        return "question_answer";
    }

    @GetMapping("/question/answer")
    public String getSingleAnswer(Model model,
                                  @RequestParam("answer") Long answerId,
                                  @RequestParam("question") Long questionId,
                                  Principal principal) {
        User user = new User();
        if (principal != null) {
            user = userService.findByUserName(principal.getName());
        } else {
            user = userService.findByUserName("Incognita");
        }
        userAnswerService.add(answerId,questionId);
        answeredUsersService.add(user.getId(),answerId);
        return "quiz_start";
    }
}
