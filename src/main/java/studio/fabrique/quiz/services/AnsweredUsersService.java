package studio.fabrique.quiz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.fabrique.quiz.entities.AnsweredUsers;
import studio.fabrique.quiz.repositories.AnsweredUsersRepository;

@Service
public class AnsweredUsersService {

    @Autowired
    private AnsweredUsersRepository answeredUsersRepository;

    public AnsweredUsers add(Long userId, Long answerId) {
        AnsweredUsers answeredUsers = new AnsweredUsers(userId,answerId);
        answeredUsersRepository.save(answeredUsers);
        return answeredUsers;
    }
}
