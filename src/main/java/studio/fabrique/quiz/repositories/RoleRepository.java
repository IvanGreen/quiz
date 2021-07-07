package studio.fabrique.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studio.fabrique.quiz.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findOneByName(String theRoleName);
}
