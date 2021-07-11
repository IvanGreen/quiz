package studio.fabrique.quiz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studio.fabrique.quiz.entities.Outcome;
import studio.fabrique.quiz.entities.QuestionAnswer;
import studio.fabrique.quiz.entities.User;
import studio.fabrique.quiz.repositories.OutcomeRepository;
import studio.fabrique.quiz.utils.OutcomeMaker;

import java.util.ArrayList;
import java.util.List;

//Service for storing user responses
@Service
public class OutcomeService {

    @Autowired
    private OutcomeRepository outcomeRepository;

    @Transactional
    public Outcome makeOutcome(OutcomeMaker outcomeMaker, User user) {
        Outcome outcome = new Outcome();
        outcome.setId(0L);
        outcome.setUser(user);
        outcome.setQuestionAnswers(new ArrayList<>(outcomeMaker.getQuestionAnswers()));
        for (QuestionAnswer q : outcomeMaker.getQuestionAnswers()) {
            q.setOutcome(outcome);
        }
        return outcome;
    }

    public List<Outcome> getAllOutcome() { return (List<Outcome>) outcomeRepository.findAll(); }

    public Outcome findById(Long id) { return outcomeRepository.findById(id).get(); }

    public void deleteById(Long id) { outcomeRepository.deleteById(id); }

    public Outcome saveOutcome(Outcome outcome) {
        Outcome outcomeOut = outcomeRepository.save(outcome);
        return outcome;
    }

    public List<Outcome> findByUser (User user) {
        List<Outcome> allOutcome = outcomeRepository.findAll();
        List<Outcome> outcomes = new ArrayList<>();

        for (Outcome o : allOutcome) {
            if (o.getUser().equals(user)) {
                outcomes.add(o);
            }
        }
        return outcomes;
    }

}
