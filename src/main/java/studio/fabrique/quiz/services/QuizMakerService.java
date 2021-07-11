package studio.fabrique.quiz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.fabrique.quiz.entities.Question;
import studio.fabrique.quiz.utils.QuizMaker;

import javax.servlet.http.HttpSession;

//Service for creating quizzes from questions
@Service
public class QuizMakerService {

    @Autowired
    private QuestionService questionService;

    public QuizMaker getCurrentMaker(HttpSession session) {
        QuizMaker quizMaker = (QuizMaker) session.getAttribute("quizMaker");
        if (quizMaker == null) {
            quizMaker = new QuizMaker();
            session.setAttribute("quizMaker",quizMaker);
        }
        return quizMaker;
    }

    public void resetQuizMaker(HttpSession session) { session.removeAttribute("quizMaker");}

    public void addToQuizMaker(HttpSession session, Long questionId) {
        Question question = questionService.getOneById(questionId);
        addToQuizMaker(session,question);
    }

    public void addToQuizMaker(HttpSession session, Question question) {
        QuizMaker quizMaker = getCurrentMaker(session);
        quizMaker.add(question);
    }

    public void removeFromQuizMaker(HttpSession session, Long questionId){
        Question question = questionService.getOneById(questionId);
        removeFromQuizMaker(session,question);
    }

    public void removeFromQuizMaker(HttpSession session, Question question){
        QuizMaker quizMaker = getCurrentMaker(session);
        quizMaker.remove(question);
    }
}
