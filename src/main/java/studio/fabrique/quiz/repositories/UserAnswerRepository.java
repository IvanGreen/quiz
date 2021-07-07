package studio.fabrique.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studio.fabrique.quiz.entities.UserAnswer;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer,Long> {
}
