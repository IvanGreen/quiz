package studio.fabrique.quiz.utils;

import lombok.Data;
import studio.fabrique.quiz.entities.Question;
import studio.fabrique.quiz.entities.QuizQuestion;

import java.util.ArrayList;
import java.util.List;

//A utility designed to create quizzes from different questions
@Data
public class QuizMaker {

    private List<QuizQuestion> questions;

    public QuizMaker() {
        questions = new ArrayList<>();
    }

    public void add(Question question){
        QuizQuestion quizQuestion = findQuizFromQuestion(question);
        if (quizQuestion == null) {
            quizQuestion = new QuizQuestion();
            quizQuestion.setQuestion(question);
            quizQuestion.setId(0L);
            questions.add(quizQuestion);
        }
        quizQuestion.setQuantity(quizQuestion.getQuantity() + 1);
    }

    public void setQuantity(Question question, int quantity){
        QuizQuestion quizQuestion = findQuizFromQuestion(question);
        if (quizQuestion == null) {
            return;
        }
        quizQuestion.setQuantity(quantity);
    }

    public void remove(Question question) {
        QuizQuestion quizQuestion = findQuizFromQuestion(question);
        if (quizQuestion == null) {
            return;
        }
        if (quizQuestion.getQuantity() > 1) {
            quizQuestion.setQuantity(quizQuestion.getQuantity() - 1);
        } else {
            questions.remove(quizQuestion);
        }
    }

    private QuizQuestion findQuizFromQuestion(Question question){
        return questions.stream().filter(o -> o.getQuestion().getId().equals(question.getId())).findFirst().orElse(null);
    }
}
