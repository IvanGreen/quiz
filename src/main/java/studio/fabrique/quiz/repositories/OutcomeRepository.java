package studio.fabrique.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studio.fabrique.quiz.entities.Outcome;

@Repository
public interface OutcomeRepository extends JpaRepository<Outcome,Long> {
}
