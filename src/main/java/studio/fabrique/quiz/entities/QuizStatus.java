package studio.fabrique.quiz.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "quiz_statuses")
public class QuizStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Override
    public String toString() {
        return "Status{" + "id=" + id + ", title='" + title + '\'' + '}';
    }
}
