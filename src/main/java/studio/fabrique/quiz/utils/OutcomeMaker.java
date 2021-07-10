package studio.fabrique.quiz.utils;

import lombok.Data;
import org.springframework.security.core.parameters.P;
import studio.fabrique.quiz.entities.*;

import java.util.ArrayList;
import java.util.List;

//тут собираем ответы
@Data
public class OutcomeMaker {
    private List<QuestionAnswer> questionAnswers;

    public OutcomeMaker() {
        questionAnswers = new ArrayList<>();
    }

    public void add(Answer answer, Question question, Quiz quiz) {
        QuestionAnswer questionAnswer = findQuestionFromAnswer(answer);
        if (questionAnswer == null) {
            questionAnswer = new QuestionAnswer();
            questionAnswer.setAnswer(answer);
            questionAnswer.setQuestion(question);
            questionAnswer.setQuiz(quiz);
            questionAnswer.setId(0L);
            questionAnswers.add(questionAnswer);
        }
    }

    private QuestionAnswer findQuestionFromAnswer(Answer answer) {
        return questionAnswers.stream().filter(o -> o.getAnswer().getId().equals(answer)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "OutcomeMaker{" +
                "questionAnswers=" + questionAnswers +
                '}';
    }
}
