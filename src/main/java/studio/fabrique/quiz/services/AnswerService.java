package studio.fabrique.quiz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.fabrique.quiz.entities.Answer;
import studio.fabrique.quiz.repositories.AnswerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired QuestionService questionService;

    public List<Answer> getAllAnswersByQuestionId(Long questionId) {
        List<Answer> allAnswers = answerRepository.findAll();
        List<Answer> answersByQuestionId = new ArrayList<>();
        for (Answer a : allAnswers){
            if (a.getQuestion().getId().equals(questionId)) {
                answersByQuestionId.add(a);
            }
        }
        return answersByQuestionId;
    }

    public Answer saveAnswer(Answer answer) { return answerRepository.save(answer); }

    public void deleteOne(Long id) { answerRepository.deleteById(id); }

    public Answer getOneById(Long id) { return answerRepository.getById(id); }

    public List<Answer> getAllAnswers() { return answerRepository.findAll(); }

}
