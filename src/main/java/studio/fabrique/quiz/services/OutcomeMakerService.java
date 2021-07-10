package studio.fabrique.quiz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studio.fabrique.quiz.entities.Answer;
import studio.fabrique.quiz.entities.Question;
import studio.fabrique.quiz.entities.Quiz;
import studio.fabrique.quiz.entities.User;
import studio.fabrique.quiz.utils.OutcomeMaker;

import javax.servlet.http.HttpSession;

//корзина для ответов
@Service
public class OutcomeMakerService {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    public OutcomeMaker getCurrentMaker(HttpSession session) {
        OutcomeMaker outcomeMaker = (OutcomeMaker) session.getAttribute("outcomeMaker");
        if (outcomeMaker == null) {
            outcomeMaker = new OutcomeMaker();
            session.setAttribute("outcomeMaker",outcomeMaker);
        }
        return outcomeMaker;
    }

    public void addToOutcomeMaker(HttpSession session, Long answerId, Long questionId) {
        OutcomeMaker outcomeMaker = getCurrentMaker(session);
        Answer answer = answerService.getOneById(answerId);
        Question question = questionService.getOneById(questionId);
        addToOutcomeMaker(session,answer,question);
    }

    public void addToOutcomeMaker(HttpSession session, Answer answer, Question question) {
        OutcomeMaker outcomeMaker = getCurrentMaker(session);
        outcomeMaker.add(answer, question);
    }
}
