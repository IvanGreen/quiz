package studio.fabrique.quiz.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_answer")
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "question_id")
    private Long questionId;

   @Column(name = "answer_id")
    private Long answer;

    public UserAnswer() {
    }

    public UserAnswer(Long questionId, Long answerId) {
        this.questionId = questionId;
        this.answer = answerId;
    }
}
