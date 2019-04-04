package com.epam.brest.project.service;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.model.StudentAnswer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml", "classpath*:dao-context.xml"})
class StudentAnswerServiceImplTest {

    private static final int STUDENT_ID = 2;

    @Autowired
    private TestDtoService testDtoService;

    @Autowired
    private StudentAnswerService studentAnswerService;

    @Test
    void findAllStudentAnswer() {
        List<StudentAnswer> studentAnswers = studentAnswerService.findStudentAnswerById(1);
        assertEquals(false, studentAnswers.get(1).getStudentAnswer());
    }

    @Test
    void addStudentAnswer() {
        TestDto testDto = testDtoService.findTestDtoById(1);
        Boolean answerBeforeAdd = testDto.getQuestions().get(0).getQuestionItems().get(0).getAnswer();
        studentAnswerService.addStudentAnswer(testDto, STUDENT_ID);
        List<StudentAnswer> studentAnswerAfterAdd = studentAnswerService.findStudentAnswerById(STUDENT_ID);
        assertEquals(answerBeforeAdd, studentAnswerAfterAdd.get(0).getStudentAnswer());
    }
}