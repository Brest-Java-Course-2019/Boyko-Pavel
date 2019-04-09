package com.epam.brest.project.dao;

import com.epam.brest.project.model.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath*:dao-context.xml"})
@Transactional
@Rollback
class QuestionDaoJdbcImplTest {

    private static final int ID_QUESTION = 3;
    private static final int DELETED_QUESTION_ID = 2;
    private static final int TEST_ID = 2;

    @Autowired
    private QuestionDao questionDao;


    @Test
    void findAllQuestion() {
        Stream<Question> question = questionDao.findAll();
        assertNotNull(question);
        assertEquals(4, question.count());
    }

    @Test
    void findQuestionByID() {
        Question question = questionDao.findById(ID_QUESTION).get();
        assertEquals("Count 3+2=", question.getQuestionName());
    }


    @Test
    void findallQuestionByTestId() {
        List<Question> questionList = questionDao.findAllQuestionByTestId(TEST_ID);
        assertEquals("Count 3+2=", questionList.get(1).getQuestionName());
    }

    @Test
    void findQuestionByDeletedID() {
        Assertions.assertThrows(DataAccessException.class, () -> {
            questionDao.findById(DELETED_QUESTION_ID);
        });
    }

    @Test
    void addQuestion() {
        Stream<Question> countIdBeforeInsert = questionDao.findAll();
        Question questionItem = new Question();
        questionItem.setQuestionName("Question №4");
        questionDao.add(questionItem, 2);
        Stream<Question> countIdAfterInsert = questionDao.findAll();

        assertEquals(1, countIdAfterInsert.count() - countIdBeforeInsert.count());
    }

    @Test
    void addWrongQuestion() {
        Stream<Question> countIdBeforeInsert = questionDao.findAll();
        Question questionItem = new Question();
        questionItem.setTestId(25);
        questionItem.setQuestionName("Question №4");
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            questionDao.add(questionItem, questionItem.getTestId());
        });
    }


    @Test
    void updateQuestion() {
        Question question = questionDao.findById(ID_QUESTION).get();
        String questionBeforeUpdate = question.getQuestionName();
        question.setQuestionName(questionBeforeUpdate + "_update");
        questionDao.update(question);
        Question questionAfterUpdate = questionDao.findById(question.getQuestionId()).get();
        assertEquals(question.getQuestionName(), questionAfterUpdate.getQuestionName());
    }


    @Test
    void batchupdateQuestion() {
        List<Question> questions = questionDao.findAll().collect(Collectors.toList());
        for (Question question : questions) {
            question.setQuestionName(question.getQuestionName() + "_update");
        }
        questionDao.batchUpdate(questions);
        List<Question> questionAfterUpdate = questionDao.findAll().collect(Collectors.toList());
        assertEquals(questions.get(1).getQuestionName(), questionAfterUpdate.get(1).getQuestionName());
    }

    @Test
    void deleteQuestionByID() {
        questionDao.deleteByTestId(1);
        assertEquals(0, questionDao.findAllQuestionByTestId(1).size());
    }
}