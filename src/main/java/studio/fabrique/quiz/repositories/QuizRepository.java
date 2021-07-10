package studio.fabrique.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import studio.fabrique.quiz.entities.Answer;
import studio.fabrique.quiz.entities.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long>, JpaSpecificationExecutor {
}
