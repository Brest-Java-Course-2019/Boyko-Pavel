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
        question.setQuestionName("new Question");
        question.setQuestionItems(createNewQuestionItems());
        questionList.add(question);
        return questionList;
    }

    List<QuestionItem> createNewQuestionItems() {
        List<QuestionItem> questionItems = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            QuestionItem questionItem = new QuestionItem();
            questionItem.setAnswer(true);
            questionItem.setDescription("new questionItem" + i);
            questionItems.add(questionItem);
        }
        return questionItems;
    }

    TestDto createNewTestDto() {
        TestDto testDTO = new TestDto();
        testDTO.setSubjectId(2);
        testDTO.setTestName("New Test");
        testDTO.setQuestions(createNewQuestion());
        testDTO.setTeacherId(1);
        return testDTO;
    }

    @Test
    void findTestDtoById() {
        TestDto testDto = testDtoService.findTestDtoById(1);
        List<Question> questions = testDto.getQuestions();
        assertEquals("Algebra", testDto.getTestName());
        assertEquals("Math", testDto.getSubjectName());
        assertEquals("Count 2+2=", questions.get(0).getQuestionName());
        assertEquals("ANSWER: 1", questions.get(0).getQuestionItems().get(0).getDescription());
    }

    @Test
    void addTestDto() {
        TestDto testDto = createNewTestDto();
        testDtoService.addTestDto(testDto);
        TestDto testDtoAfterAdd = testDtoService.findTestDtoById(5);
        assertEquals("Math", testDtoAfterAdd.getSubjectName());
        assertEquals("new Question", testDtoAfterAdd.getQuestions().get(0).getQuestionName());
    }

    @Test
    void updateTestDto() {
        TestDto testDto = testDtoService.findTestDtoById(ID_TEST_DTO);
        testDto.setTestName(testDto.getTestName() + "_update");
        String questionName = testDto.getQuestions().get(0).getQuestionName();
        List<Question> questions = testDto.getQuestions();
        questions.get(0).setQuestionName(questionName + "_update");
        questions.add(createNewQuestion().get(0));
        testDtoService.updateTestDto(testDto);
        TestDto testDtoAfterUpdate = testDtoService.findTestDtoById(ID_TEST_DTO);
        assertEquals(questionName + "_update", testDtoAfterUpdate.getQuestions().get(0).getQuestionName());
        assertEquals(2, testDto.getQuestions().size());
    }

    @Test
    void deleteTestDto() {
        testDtoService.deleteTestDto(2);
        Assertions.assertThrows(EmptyResultDataAccessException.class, () ->
                testDtoService.findTestDtoById(2));
    }
}