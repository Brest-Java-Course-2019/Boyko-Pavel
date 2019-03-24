package com.epam.brest.project.dao;

import com.epam.brest.project.model.QuestionItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath*:dao-context.xml"})
@Transactional
@Rollback
class QuestionItemDaoJdbcImplTest {

    private static final int ID_QUESTION_ITEM = 2;
    private static final int DELETED_QUESTION_ID = 3;
    private static final int DELETE_QUESTION_ITEM = 4;

    @Autowired
    private QuestionItemDao questionItemDao;

    @Test
    void findAllQuestionItem() {
        Stream<QuestionItem> questionItems = questionItemDao.findall();
        assertNotNull(questionItems);
        assertEquals(6, questionItems.count());
    }

    @Test
    void findQuestionItemByID() {
        QuestionItem questionItem = questionItemDao.findById(ID_QUESTION_ITEM).get();

        assertEquals("ANSWER: 2", questionItem.getDescription());
    }

    @Test
    void findallQuestionByTestId(){
        List<QuestionItem> questionList = questionItemDao.findallQuestionItemByQuestionId(2);
        assertEquals("ANSWER: 7", questionList.get(0).getDescription());
    }

    @Test
    void findQuestionItemByDeletedID() {
        Assertions.assertThrows(DataAccessException.class, () -> {
            questionItemDao.findById(DELETED_QUESTION_ID);
        });
    }

    @Test
    void addQuestionItem() {
        Stream<QuestionItem> countIdBeforeInsert = questionItemDao.findall();
        QuestionItem questionItem = new QuestionItem();
        questionItem.setQuestionId(3);
        questionItem.setDescription("Answer: 7");
        questionItem.setAnswer(false);
        questionItemDao.add(questionItem);
        Stream<QuestionItem> countIdAfterInsert = questionItemDao.findall();

        assertEquals(1, countIdAfterInsert.count() - countIdBeforeInsert.count());
    }


    @Test
    void updateQuestionItem() {
        QuestionItem questionItem = questionItemDao.findById(ID_QUESTION_ITEM).get();
        String descriptionBeforeUpdate = questionItem.getDescription();
        questionItem.setDescription(descriptionBeforeUpdate + "_update");
        questionItemDao.update(questionItem);
        QuestionItem questionItemAfterUpdate = questionItemDao.findById(questionItem.getQuestionItemId()).get();
        assertEquals(questionItem.getDescription(), questionItemAfterUpdate.getDescription());
    }


    @Test
    void deleteQuestionItemByID() {
        questionItemDao.delete(DELETE_QUESTION_ITEM);
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            questionItemDao.findById(DELETE_QUESTION_ITEM);
        });
    }


    @Test
    void batchDeleteQuestionItem() {
        List<List<QuestionItem>> questionList = new ArrayList<>();
        List<QuestionItem> questionItems = new ArrayList<>();
        questionItems.add(questionItemDao.findById(ID_QUESTION_ITEM).get());
        questionList.add(questionItems);
        questionItemDao.batchDelete(questionList);
        Assertions.assertThrows(RuntimeException.class, () -> {
            questionItemDao.findById(ID_QUESTION_ITEM);
        });
    }

    @Test
    void bathUpdateQuestionItem() {
        String questionBeforeUpdate;
        List<List<QuestionItem>> questionList = new ArrayList<>();
        List<QuestionItem> questionItems = new ArrayList<>();
        QuestionItem questionItem = questionItemDao.findById(ID_QUESTION_ITEM).get();
        questionBeforeUpdate = questionItem.getDescription();
        questionItem.setDescription(questionBeforeUpdate + "update");
        questionItems.add(questionItem);
        questionList.add(questionItems);
        questionItemDao.batchUpdate(questionList);
        QuestionItem newQuestionItem = questionItemDao.findById(ID_QUESTION_ITEM).get();
        assertEquals(questionBeforeUpdate +"update", newQuestionItem.getDescription());
    }
}