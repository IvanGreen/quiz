package studio.fabrique.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studio.fabrique.quiz.entities.AnsweredUsers;

@Repository
public interface AnsweredUsersRepository extends JpaRepository<AnsweredUsers,Long> {
}
