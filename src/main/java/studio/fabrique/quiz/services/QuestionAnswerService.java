package studio.fabrique.quiz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.fabrique.quiz.entities.Outcome;
import studio.fabrique.quiz.entities.QuestionAnswer;
import studio.fabrique.quiz.repositories.QuestionAnswerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionAnswerService {

    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;

    public List<QuestionAnswer> getAllQuestionAnswers() { return questionAnswerRepository.findAll(); }

    public List<QuestionAnswer> getAllQuestionAnswersByOutcome(List<Outcome> outcomes) {
        List<QuestionAnswer> questionAnswers = new ArrayList<>();
        List<QuestionAnswer> allQuestionAnswers = getAllQuestionAnswers();
        for (Outcome o : outcomes) {
            for (int i = 0; i < allQuestionAnswers.size(); i++) {
                if (o.getId().equals(allQuestionAnswers.get(i).getOutcome().getId())){
                    questionAnswers.add(allQuestionAnswers.get(i));
                }
            }
        }
        return questionAnswers;
    }
}
