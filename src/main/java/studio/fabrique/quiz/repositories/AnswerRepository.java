package studio.fabrique.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import studio.fabrique.quiz.entities.Answer;
import studio.fabrique.quiz.entities.Question;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long>, JpaSpecificationExecutor {
}
