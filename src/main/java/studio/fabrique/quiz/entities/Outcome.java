package studio.fabrique.quiz.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

//тут будет финальный ответ
@Entity
@Data
@Table(name = "outcome")
public class Outcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToMany
    @JoinColumn(name = "questions_id")
    private List<Question> questions;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;
}
