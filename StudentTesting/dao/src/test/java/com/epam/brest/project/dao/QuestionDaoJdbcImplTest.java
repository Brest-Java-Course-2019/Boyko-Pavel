package com.epam.brest.project.dao;

import com.epam.brest.project.model.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath*:dao-context.xml"})
@Transactional
@Rollback
class QuestionDaoJdbcImplTest {

    private static final int ID_QUESTION = 3;
    public static final int DELETED_QUESTION_ID = 2;

    @Autowired
    @Qualifier("questionDaoJdbcImpl")
    private QuestionDao questionDao;

    @Test
    void findAllQuestion() {
        Stream<Question> question = questionDao.findall();
        assertNotNull(question);
        assertEquals(3, question.count());
    }

    @Test
    void findQuestionByID(){
        Question question = questionDao.findById(ID_QUESTION).get();
        assertEquals("Count 3+2=", question.getQuestion());
    }

    @Test
    void findQuestionByDeletedID(){
        Assertions.assertThrows(DataAccessException.class, () -> {
            questionDao.findById(DELETED_QUESTION_ID);
        });
    }

    @Test
    void addQuestion(){
        Stream<Question> countIdBeforeInsert = questionDao.findall();
        Question questionItem = new Question();
        questionItem.setTestId(2);
        questionItem.setQuestion("Question №4");
        questionDao.add(questionItem);
        Stream<Question> countIdAfterInsert = questionDao.findall();

        assertEquals(1 , countIdAfterInsert.count() - countIdBeforeInsert.count());
    }


    @Test
    void updateQuestion(){
        Question question = questionDao.findById(ID_QUESTION).get();
        String questionBeforeUpdate = question.getQuestion();
        question.setQuestion(questionBeforeUpdate +"_update");
        questionDao.update(question);
        Question questionAfterUpdate = questionDao.findById(question.getQuestionId()).get();
        assertEquals(question.getQuestion(), questionAfterUpdate.getQuestion());
    }


    @Test
    void deleteQuestionByID(){
        questionDao.delete(ID_QUESTION);
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            questionDao.findById(ID_QUESTION);
        });
    }

    @Test
    void deleteQuestionNotExistID(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            questionDao.delete(34343434);
        });
    }


}