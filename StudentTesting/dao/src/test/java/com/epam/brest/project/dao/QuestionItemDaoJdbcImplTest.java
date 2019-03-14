//package com.epam.brest.project.dao;
//
//import com.epam.brest.project.model.QuestionItem;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath*:dao-context.xml"})
//@Transactional
//@Rollback
//class QuestionItemDaoJdbcImplTest {
//
//    private static final int ID_QUESTION_ITEM = 3;
//    @Autowired
//    private QuestionItemDaoJdbcImpl questionItemDao;
//
//    @Test
//    void findAllQuestionItem() {
//        Stream<QuestionItem> questionItems = questionItemDao.findall();
//        assertNotNull(questionItems);
//        assertEquals(5, questionItems.count());
//    }
//
//    @Test
//    void findQuestionItemByID(){
//        QuestionItem questionItem = questionItemDao.findById(ID_QUESTION_ITEM).get();
//        assertEquals("ANSWER: 3", questionItem.getDescription());
//    }
//
//    @Test
//    void addQuestionItem(){
//        Stream<QuestionItem> countIdBeforeInsert = questionItemDao.findall();
//
//        QuestionItem questionItem = new QuestionItem();
//        questionItem.setQuestionId(2);
//        questionItem.setDescription("Answer: 7");
//        questionItem.setAnswer(false);
//        questionItemDao.add(questionItem);
//        Stream<QuestionItem> countIdAfterInsert = questionItemDao.findall();
//
//        assertEquals(1 , countIdAfterInsert.count() - countIdBeforeInsert.count());
//    }
//
//    @Test
//    void updateQuestionItem(){
////        QuestionItem questionItem = new QuestionItem();
//        QuestionItem questionItem = questionItemDao.findById(1).get();
//        String descriptionBeforeUpdate = questionItem.getDescription();
//        questionItem.setDescription(descriptionBeforeUpdate +"_update");
//        questionItemDao.update(questionItem);
//        QuestionItem questionItemAfterUpdate = questionItemDao.findById(questionItem.getQuestionItemId()).get();
//        assertEquals(questionItem.getDescription(), questionItemAfterUpdate.getDescription());
//    }
//}