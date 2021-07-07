package studio.fabrique.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studio.fabrique.quiz.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByUserName(String userName);
}
