package studio.fabrique.quiz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.fabrique.quiz.entities.QuizStatus;
import studio.fabrique.quiz.repositories.QuizStatusRepository;

@Service
public class QuizStatusService {

    @Autowired
    private QuizStatusRepository quizStatusRepository;

    public QuizStatus getStatusById(Long id) { return quizStatusRepository.findById(id).orElse(null); }
}
