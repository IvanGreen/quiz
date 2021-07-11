package studio.fabrique.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import studio.fabrique.quiz.entities.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long>, JpaSpecificationExecutor {
}
