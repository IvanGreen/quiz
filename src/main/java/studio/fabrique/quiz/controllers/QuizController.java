package studio.fabrique.quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import studio.fabrique.quiz.entities.*;
import studio.fabrique.quiz.services.*;
import studio.fabrique.quiz.utils.OutcomeMaker;

import javax.servlet.http.HttpSession;
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
    private OutcomeMakerService outcomeMakerService;

    @Autowired
    private AnswerService answerService;

    @GetMapping("/showQuzzes")
    public String showQuzzes(Model model, HttpSession session){
        List<Quiz> quizzes = quizService.getAllConfirmedQuiz();
        OutcomeMaker outcomeMaker = outcomeMakerService.getCurrentMaker(session);
        model.addAttribute("outcomeMaker", outcomeMaker);
        model.addAttribute("quizzes",quizzes);
        return "quizzes_page";
    }

    @GetMapping("/start/{id}")
    public String startQuiz(Model model,
                            Principal principal,
                            @PathVariable("id") Long quizId,
                            HttpSession session){
        OutcomeMaker outcomeMaker = outcomeMakerService.getCurrentMaker(session);
        List<Question> questionList = quizQuestionService.getQuestionsByQuizId(quizId);
        if (outcomeMaker.getQuestionAnswers() != null) {
            for (QuestionAnswer q : outcomeMaker.getQuestionAnswers()) {
                for (int i = 0; i < questionList.size(); i++) {
                    if (questionList.get(i).getId().equals(q.getQuestion().getId()) && questionList.size() > 0) {
                        questionList.remove(questionService.getOneById(q.getQuestion().getId()));
                    }
                }
            }
        }
        Quiz quiz = quizService.findById(quizId);
        model.addAttribute("questionList",questionList);
        model.addAttribute("quiz",quiz);
        model.addAttribute("outcomeMaker",outcomeMaker);
        return "quiz_start";
    }

    @GetMapping("question/answer/{userChoiceQuestion}/{quizId}")
    public String questionAnswer(Model model,
                                 @PathVariable("userChoiceQuestion") Long userChoiceQuestion,
                                 @PathVariable("quizId") Long quizId,
                                 HttpSession session) {
        Question question = questionService.getOneById(userChoiceQuestion);
        List<Answer> answers = answerService.getAllAnswersByQuestionId(question.getId());
        model.addAttribute("question",question);
        model.addAttribute("quizId",quizId);
        model.addAttribute("answers",answers);
        return "question_answer";
    }

    @GetMapping("question/answer")
    public String answerToQuestion(Model model,
                                   @RequestParam("answer") Long answerId,
                                   @RequestParam("question") Long questionId,
                                   @RequestParam("quiz") Long quizId,
                                   HttpSession httpSession) {
        outcomeMakerService.addToOutcomeMaker(httpSession,answerId,questionId);
        OutcomeMaker outcomeMaker = outcomeMakerService.getCurrentMaker(httpSession);
        model.addAttribute("outcomeMaker", outcomeMaker);
        model.addAttribute("quizId",quizId);
        return "success_answer";
    }

}
