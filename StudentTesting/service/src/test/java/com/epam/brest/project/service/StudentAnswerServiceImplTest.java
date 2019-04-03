package com.epam.brest.project.service;

import com.epam.brest.project.dao.QuestionItemDao;
import com.epam.brest.project.model.StudentAnswer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml", "classpath*:dao-context.xml"})
class StudentAnswerServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);
    private static final int STUDENT_ID = 1;

    @Autowired
    private QuestionItemDao questionItemDao;

    @Autowired
    private StudentAnswerService studentAnswerService;

    @Test
    void findAllStudentAnswer() {
        List<StudentAnswer> studentAnswers = studentAnswerService.findStudentAnswerById(1);
        assertEquals(false, studentAnswers.get(1).getStudentAnswer());
    }

//    @Test
//    void addStudentAnswer() {
//        TestDto testDto = new TestDto();
//        List<QuestionItem> questionItemsToAdd = new ArrayList<>();
//        for (QuestionItem questionItem : questionItemDao.findAllQuestionItemByTestId(1) ) {
//            QuestionItem questionItemToAdd = new QuestionItem();
//            questionItemToAdd.setAnswer(false);
//            questionItemToAdd.setQuestionItemId(questionItem.getQuestionItemId());
//            questionItemsToAdd.add(questionItemToAdd);
//        }
//        List<List<QuestionItem>> listList = new ArrayList<>();
//        listList.add(questionItemsToAdd);
//        testDto.setQuestionItems(listList);
//        studentAnswerService.addStudentAnswer(testDto, STUDENT_ID);
//        List<StudentAnswer> a = studentAnswerService.findStudentAnswerById(STUDENT_ID);
//        assertEquals(false, studentAnswerService.findStudentAnswerById(STUDENT_ID)
//                .get(4).getStudentAnswer());
//    }


}