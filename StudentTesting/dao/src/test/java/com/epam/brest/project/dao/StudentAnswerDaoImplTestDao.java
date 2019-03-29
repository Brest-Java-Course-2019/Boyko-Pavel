package com.epam.brest.project.dao;

import com.epam.brest.project.model.QuestionItem;
import com.epam.brest.project.model.StudentAnswer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath*:dao-context.xml"})
@Transactional
@Rollback
class StudentAnswerDaoImplTestDao {

    @Autowired
    private StudentAnswerDao studentAnswerDao;
    @Autowired
    private QuestionItemDao questionItemDao;

    List<StudentAnswer> createStudentAnswer(){
        List<StudentAnswer> studentAnswers = new ArrayList<>();
        for (QuestionItem i : questionItemDao.findAllQuestionItemByTestId(1) ) {
            StudentAnswer studentAnswer = new StudentAnswer();
            studentAnswer.setStudentAnswer(false);
            studentAnswer.setStudentId(1);
            studentAnswer.setQuestionItemId(i.getQuestionItemId());
            studentAnswers.add(studentAnswer);
        }
        return studentAnswers;
    }

    @Test
    void findStudentAnswerByIdStudent(){
        List<StudentAnswer> studentAnswers = studentAnswerDao.findStudentAnswerById(1);
        assertEquals(4, studentAnswerDao.findStudentAnswerById(1).size());
        assertEquals(true, studentAnswerDao.findStudentAnswerById(1).get(3).getStudentAnswer());
    }

    @Test
    void  addStudentAnswer(){
        List<StudentAnswer> studentAnswersBeforeAdd = createStudentAnswer();
        studentAnswerDao.addStudentAnswer(studentAnswersBeforeAdd);
                studentAnswerDao.findStudentAnswerById(1);
        List<StudentAnswer> studentAnswersAfterAdd = studentAnswerDao.findStudentAnswerById(1);
        assertEquals(studentAnswersBeforeAdd.get(1).getStudentAnswer(), studentAnswersAfterAdd.get(4).getStudentAnswer());
    }
}