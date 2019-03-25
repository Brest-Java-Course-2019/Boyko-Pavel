package com.epam.brest.project.service;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.model.Question;
import com.epam.brest.project.model.QuestionItem;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml", "classpath*:dao-context.xml"})
class TestDtoServiceImplTest {

    private static final int ID_TEST_DTO = 1;
    @Autowired
    private TestDtoService testDtoService;

    List<Question> createNewQuestion() {
        List<Question> questionList = new ArrayList<>();
        Question question = new Question();
        question.setQuestionName("square triangle");
        questionList.add(question);
        return questionList;
    }

    List<List<QuestionItem>> createNewQuestionitem() {
        List<List<QuestionItem>> questionItemList = new ArrayList<>();
        List<QuestionItem> questionItems = new ArrayList<>();
        QuestionItem questionItem = new QuestionItem();
        questionItem.setDescription("0.5*h*b");
        questionItem.setAnswer(true);
        questionItems.add(questionItem);
        questionItemList.add(questionItems);
        return questionItemList;
    }

    TestDto createNewTestDto() {
        TestDto testDTO = new TestDto();
        testDTO.setSubjectId(2);
        testDTO.setSubjectName("434g34g");
        testDTO.setTestName("New Test");
        testDTO.setQuestions(createNewQuestion());
        testDTO.setQuestionItems(createNewQuestionitem());
        testDTO.setTeacherId(1);
        return testDTO;
    }

    @Test
    void findTestDtoById() {
        TestDto testDTO = testDtoService.findTestDtoById(1);
        assertEquals("Algebra", testDTO.getTestName());
        assertEquals("Math", testDTO.getSubjectName());
        assertEquals("Count 2+2=", testDTO.getQuestions().get(0).getQuestionName());
        assertEquals("ANSWER: 4", testDTO.getQuestionItems().get(0).get(2).getDescription());
    }

    @Test
    void addTestDto() {
        TestDto testDTO = createNewTestDto();
        testDtoService.addTestDto(testDTO);
        assertEquals("Math", testDtoService.findTestDtoById(5).getSubjectName());
        assertEquals("0.5*h*b", testDtoService.findTestDtoById(5).getQuestionItems().get(0).get(0).getDescription());
    }

    @Test
    void updateTestDto() {
        TestDto testDTO = testDtoService.findTestDtoById(ID_TEST_DTO);
        String testName = testDTO.getTestName();
        testDTO.setTestName(testName + "_update");
        testDtoService.updateTestDto(testDTO);
        TestDto testDtoAfterUpdate = testDtoService.findTestDtoById(ID_TEST_DTO);
        assertEquals(testName + "_update", testDtoAfterUpdate.getTestName());
    }

    @Test
    void deleteTestDto() {
        testDtoService.deleteTestDto(2);
        Assertions.assertThrows(EmptyResultDataAccessException.class, () ->
            testDtoService.findTestDtoById(2));
    }
}