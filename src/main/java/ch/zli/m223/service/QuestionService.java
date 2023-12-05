package ch.zli.m223.service;

import ch.zli.m223.model.Question;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class QuestionService {

    @Inject
    EntityManager entityManager;

    @Transactional
    public Question createQuestion(Question question) {
        entityManager.persist(question);
        return question;
    }

    @Transactional
    public Question updateQuestion(Long questionID, Question updatedQuestion) {
        Question question = entityManager.find(Question.class, questionID);
        if (question != null) {
            question.setQuestion(updatedQuestion.getQuestion());
            question.setMember(updatedQuestion.getMember());
            entityManager.merge(question);
            return question;
        } else {
            throw new IllegalStateException("Question not found with ID: " + questionID);
        }
    }

    @Transactional
    public void deleteQuestion(Long questionID) {
        Question question = entityManager.find(Question.class, questionID);
        if (question != null) {
            entityManager.remove(question);
        } else {
            throw new IllegalStateException("Question not found with ID: " + questionID);
        }
    }

    public List<Question> getAllQuestions() {
        return entityManager.createQuery("SELECT q FROM Question q", Question.class).getResultList();
    }
}
