package studio.fabrique.quiz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.fabrique.quiz.entities.Question;
import studio.fabrique.quiz.entities.QuizQuestion;
import studio.fabrique.quiz.repositories.QuizQuestionRepository;

import java.util.ArrayList;
import java.util.List;

//Service for searching questions using quiz
@Service
public class QuizQuestionService {

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    public List<Question> getQuestionsByQuizId(Long id){
        List<QuizQuestion> allQuizQuestionList = quizQuestionRepository.findAll();
        List<Question> questionList = new ArrayList<>();
        for (QuizQuestion q : allQuizQuestionList) {
            if (q.getQuiz().getId() == id){
                questionList.add(q.getQuestion());
            }
        }
        return questionList;
    }
}
