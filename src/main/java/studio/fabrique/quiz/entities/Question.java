package studio.fabrique.quiz.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private String type;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "question", fetch = FetchType.EAGER)
    private List<Answer> answers;

}
