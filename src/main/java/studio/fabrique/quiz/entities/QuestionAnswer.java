package studio.fabrique.quiz.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "question_answer")
@Data
public class QuestionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "outcome_id")
    private Outcome outcome;
}
