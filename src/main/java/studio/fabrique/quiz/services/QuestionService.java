package studio.fabrique.quiz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studio.fabrique.quiz.entities.Question;
import studio.fabrique.quiz.repositories.QuestionRepository;

import java.util.List;

@Service
@Transactional
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Page<Question> findAll(Pageable pageable){
        return questionRepository.findAll(pageable);
    }

    public Question getOneById(Long id) { return questionRepository.getById(id); }

    public List<Question> getAllQuestions() { return questionRepository.findAll(); }

    public Question saveQuestion(Question question) { return questionRepository.save(question); }

    public void deleteAll(){ questionRepository.deleteAll(); }

    public void updateOne(Question question) {
        Question oldQuestion = questionRepository.getById(question.getId());

        if (question.getTitle() != null) oldQuestion.setTitle(question.getTitle());

        saveQuestion(oldQuestion);
    }
}
