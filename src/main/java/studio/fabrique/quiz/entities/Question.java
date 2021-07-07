package studio.fabrique.quiz.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "questions")
public class Question implements Serializable {

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

    public Question(String title, String type) {
        this.title = title;
        this.type = type;
    }

    public Question() {
    }
}
