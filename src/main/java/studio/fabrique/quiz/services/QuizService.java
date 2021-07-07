package studio.fabrique.quiz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studio.fabrique.quiz.entities.Quiz;
import studio.fabrique.quiz.entities.QuizQuestion;
import studio.fabrique.quiz.repositories.QuizRepository;
import studio.fabrique.quiz.utils.QuizMaker;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired QuizStatusService quizStatusService;

    @Transactional
    public Quiz makeQuiz(QuizMaker quizMaker) {
        Quiz quiz = new Quiz();
        quiz.setId(0L);
        quiz.setQuizStatus(quizStatusService.getStatusById(1L));
        quiz.setQuestionList(new ArrayList<>(quizMaker.getQuestions()));
        for (QuizQuestion o : quizMaker.getQuestions()) {
            o.setQuiz(quiz);
        }
        return quiz;
    }

    public List<Quiz> getAllQuiz() { return (List<Quiz>) quizRepository.findAll(); }

    public List<Quiz> getAllConfirmedQuiz() {
        List<Quiz> allQuiz = quizRepository.findAll();
        List<Quiz> confirmedQuiz = new ArrayList<>();
        for (Quiz q : allQuiz){
            if (q.getQuizStatus().getId() == 2){
                confirmedQuiz.add(q);
            }
        }
        return confirmedQuiz;
    }

    public Quiz findById(Long id) { return  quizRepository.findById(id).get(); }

    public void deleteById(Long id) { quizRepository.deleteById(id); }

    public Quiz saveQuiz(Quiz quiz) {
        Quiz quizOut = quizRepository.save(quiz);
        quizOut.setConfirmed(true);
        return quizOut;
    }

    public Quiz changeQuizStatus(Quiz quiz, Long statusId) {
        quiz.setQuizStatus(quizStatusService.getStatusById(statusId));
        return saveQuiz(quiz);
    }
}
