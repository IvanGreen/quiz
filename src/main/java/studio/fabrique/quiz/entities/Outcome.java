package studio.fabrique.quiz.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

//тут будет финальный ответ
@Entity
@Data
@Table(name = "outcomes")
public class Outcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "outcome", fetch = FetchType.EAGER)
    private List<QuestionAnswer> questionAnswers;

}
