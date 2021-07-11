package studio.fabrique.quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import studio.fabrique.quiz.entities.Answer;
import studio.fabrique.quiz.entities.Question;
import studio.fabrique.quiz.entities.Quiz;
import studio.fabrique.quiz.services.*;
import studio.fabrique.quiz.utils.QuizMaker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizMakerService quizMakerService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuizService quizService;

    //http://localhost:8189/app/admin/allQuestions
    @GetMapping("/allQuestions")
    public String showAllQuestions(Model model,
                                   @RequestParam Map<String,String> params){
        int pageIndex = 0;
        if (params.containsKey("p")) {
            pageIndex = Integer.parseInt(params.get("p")) - 1;
        }
        Pageable pageable = PageRequest.of(pageIndex,5);
        Page<Question> page = questionService.findAll(pageable);
        model.addAttribute("page",page);
        return "all_questions";
    }


    //http://localhost:8189/app/admin/add_new_question
    //form for adding a new question
    @GetMapping("/add_new_question")
    public String getQuestionAddForm(Model model) {
        return "question_add_form";
    }

    //adding a new question
    @PostMapping("/question_process_form")
    public String questionProcessForm(Model model,
                                      @RequestParam("title") String title,
                                      @RequestParam("answer_type") String answerType) {
        if (title == null || answerType == null) {
            return "question_add_form";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setType(answerType);
        questionService.saveQuestion(question);
        model.addAttribute("question",question);
        return "success_add_question";
    }

    //http://localhost:8189/app/admin/add_answer/{id}
    //form for adding a new answer
    @GetMapping("/add_answer/{id}")
    public String addAnswer(Model model,
                            @PathVariable("id") Long id){
        Question question = questionService.getOneById(id);
        model.addAttribute("question",question);
        return "answer_add_form";
    }

    //adding a new response
    @PostMapping("/add_answer/{id}")
    public String answerProcessForm(Model model,
                                    @RequestParam("answer") String title,
                                    @PathVariable("id") Long questionId){
        Question question = questionService.getOneById(questionId);
        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setTitle(title);
        answerService.saveAnswer(answer);
        model.addAttribute("questionId",questionId);
        return "success_add_answer";
    }

    //http://localhost:8189/app/admin/quiz/add/{id}
    //button for adding a question to Quiz
    @GetMapping("quiz/add/{id}")
    public String addToQuiz(Model model,
                            @PathVariable("id") Long id,
                            HttpSession httpSession) {
        quizMakerService.addToQuizMaker(httpSession,id);
        QuizMaker quizMaker = quizMakerService.getCurrentMaker(httpSession);
        model.addAttribute("quizMaker",quizMaker);
        return "quiz_maker";
    }

    //http://localhost:8189/app/admin/quiz/delete/{id}
    //deleting a question inside a quiz
    @GetMapping("quiz/delete")
    public String deleteOneQuestion(Model model,
                                    @RequestParam(value = "id") Long id,
                                    HttpSession httpSession) {
        quizMakerService.removeFromQuizMaker(httpSession,id);
        QuizMaker quizMaker = quizMakerService.getCurrentMaker(httpSession);
        model.addAttribute("quizMaker",quizMaker);
        return "quiz_maker";
    }

    //http://localhost:8189/app/admin/quiz/fill
    //making a quiz of questions
    @GetMapping("quiz/fill")
    public String quizFill(Model model, HttpServletRequest httpServletRequest){
        Quiz quiz = quizService.makeQuiz(quizMakerService.getCurrentMaker(httpServletRequest.getSession()));
        model.addAttribute("quiz",quiz);
        return "quiz_filler";
    }

    //http://localhost:8189/app/admin/quiz/confirm
    //confirmation of the quiz
    @PostMapping("quiz/confirm")
    public String quizConfirm(Model model,
                              HttpServletRequest httpServletRequest,
                              @ModelAttribute(name = "quiz") Quiz quizFromFrontend,
                              @RequestParam(name = "quizName") String quizName) {
        //Protection, it is required to name
        if (quizName.equals("")) {
            return "redirect:/admin/quiz/fill";
        }
        Quiz quiz = quizService.makeQuiz(quizMakerService.getCurrentMaker(httpServletRequest.getSession()));
        quiz.setQuizName(quizName);
        quiz = quizService.saveQuiz(quiz);
        model.addAttribute("quiz",quiz);
        model.addAttribute("quizName",quizName);
        return "quiz_filler";
    }

    //http://localhost:8189/app/admin/quiz/result/{id}
//confirmation of the quiz
    @GetMapping("quiz/result/{id}")
    public String quizConfirm(Model model, @PathVariable(name = "id") Long id) {

        Quiz confirmedQuiz = quizService.findById(id);
        model.addAttribute("quiz",confirmedQuiz);
        return "quiz_result";
    }

    //http://localhost:8189/app/admin/quiz/ready/{id}
    @GetMapping("quiz/ready/{id}")
    public void quizready(HttpServletRequest httpServletRequest,
                          HttpServletResponse response,
                          @PathVariable Long id) throws Exception {
        Quiz quiz = quizService.findById(id);
        quizService.changeQuizStatus(quiz,2L);
        response.sendRedirect(httpServletRequest.getHeader("referer"));
    }

    //http://localhost:8189/app/admin/showQuzzes
    //displaying the finished quizzes
    @GetMapping("/showQuizzes")
    public String showQuzzes(Model model){
        List<Quiz> quizzes = quizService.getAllQuiz();
        model.addAttribute("quizzes",quizzes);
        return "quizzes_page";
    }

    //http://localhost:8189/app/admin/quiz/delete/{id}
    //deleting the finished quiz
    @GetMapping("/quiz/delete/{id}")
    public void deleteQuiz(HttpServletRequest httpServletRequest,
                           HttpServletResponse response,
                           @PathVariable Long id) throws Exception {
        quizService.deleteById(id);
        response.sendRedirect(httpServletRequest.getHeader("referer"));
    }
}

