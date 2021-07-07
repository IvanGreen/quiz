package studio.fabrique.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studio.fabrique.quiz.entities.QuizQuestion;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion,Long> {
}
