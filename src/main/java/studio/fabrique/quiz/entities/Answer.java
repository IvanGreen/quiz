package studio.fabrique.quiz.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

}
