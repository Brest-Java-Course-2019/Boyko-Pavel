package com.epam.brest.project.dao;

import com.epam.brest.project.model.QuestionItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
        assertEquals(7, questionItems.count());
    }

    @Test
    void findQuestionItemByID() {
        QuestionItem questionItem = questionItemDao.findById(ID_QUESTION_ITEM).get();

        assertEquals("ANSWER: 2", questionItem.getDescription());
    }

    @Test
    void findallQuestionByTestId() {
        List<QuestionItem> questionList = questionItemDao.findAllQuestionItemByTestId(1);
        assertEquals("ANSWER: 1", questionList.get(0).getDescription());
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
        int sizeBeforeDelete = questionItemDao.findall().collect(Collectors.toList()).size();
        questionItemDao.deleteByTestId(1);
        int sizeAfterDelete = questionItemDao.findall().collect(Collectors.toList()).size();
        assertEquals((sizeAfterDelete + 4), sizeBeforeDelete);
    }


    @Test
    void bathUpdateQuestionItem() {
        List<QuestionItem> list = questionItemDao.findAllQuestionItemByTestId(1);
        for (QuestionItem i : list) {
            i.setDescription(i.getDescription() + "_update");
            i.setAnswer(true);
        }
        List<List<QuestionItem>> updateList = new ArrayList<>();
        updateList.add(list);
        questionItemDao.batchUpdate(updateList);
        List<QuestionItem> questionItemUpdate = questionItemDao.findAllQuestionItemByTestId(1);
        assertEquals(list.get(1).getDescription(), questionItemUpdate.get(1).getDescription());
        assertSame(list.get(2).getAnswer(), questionItemUpdate.get(2).getAnswer());
    }
}