package studio.fabrique.quiz.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quiz_name")
    private String quizName;

    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "finished_at")
    @CreationTimestamp
    private LocalDateTime finishedAt;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "quiz", fetch = FetchType.EAGER)
    private List<QuizQuestion> questionList;

    @ManyToOne
    @JoinColumn(name = "quiz_status")
    private QuizStatus quizStatus;

    @JsonIgnore
    @Transient
    private boolean confirmed;
}
