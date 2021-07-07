package studio.fabrique.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studio.fabrique.quiz.entities.QuizStatus;

@Repository
public interface QuizStatusRepository extends JpaRepository<QuizStatus,Long> {
}
