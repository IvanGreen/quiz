package studio.fabrique.quiz.services;


import org.springframework.security.core.userdetails.UserDetailsService;
import studio.fabrique.quiz.entities.User;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);
}
