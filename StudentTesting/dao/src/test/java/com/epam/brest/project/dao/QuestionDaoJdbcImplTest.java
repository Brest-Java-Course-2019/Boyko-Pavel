package com.epam.brest.project.dao;

import com.epam.brest.project.model.Question;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    private static final int DELETE_TEST_BY_ID = 4;
    private static final int ID_QUESTION = 3;
    private static final int DELETED_QUESTION_ID = 2;
    private static final int TEST_ID = 2;

    @Autowired
    @Qualifier("questionDaoJdbcImpl")
    private QuestionDao questionDao;


    @Test
    void findAllQuestion() {
        Stream<Question> question = questionDao.findall();
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
        List<Question> questionList = questionDao.findallQuestionByTestId(TEST_ID);
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
        Stream<Question> countIdBeforeInsert = questionDao.findall();
        Question questionItem = new Question();
        questionItem.setTestId(2);
        questionItem.setQuestionName("Question №4");
        questionDao.add(questionItem);
        Stream<Question> countIdAfterInsert = questionDao.findall();

        assertEquals(1, countIdAfterInsert.count() - countIdBeforeInsert.count());
    }

    @Test
    void addWrongQuestion() {
        Stream<Question> countIdBeforeInsert = questionDao.findall();
        Question questionItem = new Question();
        questionItem.setTestId(25);
        questionItem.setQuestionName("Question №4");
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            questionDao.add(questionItem);
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
        Question question1 = questionDao.findById(1).get();
        Question question2 = questionDao.findById(3).get();


        String questionBeforeUpdate1 = question1.getQuestionName();
        question1.setQuestionName(questionBeforeUpdate1 + "_update");
        String questionBeforeUpdate2 = question2.getQuestionName();
        question2.setQuestionName(questionBeforeUpdate2 + "_update");
        List<Question> questionList = new ArrayList<>();
        questionList.add(question1);
        questionList.add(question2);
        questionDao.batchUpdate(questionList);
        Question questionAfterUpdate1 = questionDao.findById(question1.getQuestionId()).get();
        Question questionAfterUpdate2 = questionDao.findById(question2.getQuestionId()).get();
        assertEquals(question1.getQuestionName(), questionAfterUpdate1.getQuestionName());
        assertEquals(question2.getQuestionName(), questionAfterUpdate2.getQuestionName());
    }

    @Test
    void deleteQuestionByID() {
        questionDao.deleteByTestId(1);
        assertEquals(0, questionDao.findallQuestionByTestId(1).size());
    }
}