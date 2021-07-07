package studio.fabrique.quiz.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "answered_users")
public class AnsweredUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_answer_id")
    private Long userAnswerId;

    public AnsweredUsers(Long userId, Long userAnswerId) {
        this.userId = userId;
        this.userAnswerId = userAnswerId;
    }

    public AnsweredUsers() {
    }
}
