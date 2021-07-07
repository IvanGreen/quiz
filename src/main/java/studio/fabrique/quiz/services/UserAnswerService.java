package studio.fabrique.quiz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.fabrique.quiz.entities.Answer;
import studio.fabrique.quiz.entities.Question;
import studio.fabrique.quiz.entities.UserAnswer;
import studio.fabrique.quiz.repositories.UserAnswerRepository;

@Service
public class UserAnswerService {

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    public UserAnswer add (Long answerId, Long questionId){
        UserAnswer userAnswer = new UserAnswer(questionId,answerId);
        userAnswerRepository.save(userAnswer);
        return userAnswer;
    }
}
