package com.epam.brest.project.rest_app;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.model.Question;
import com.epam.brest.project.model.QuestionItem;
import com.epam.brest.project.model.StudentAnswer;
import com.epam.brest.project.service.StudentAnswerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
class StudentAnswerRestControllerTest {

    @Autowired
    private StudentAnswerRestController studentAnswerRestController;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private StudentAnswerService studentAnswerService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentAnswerRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @AfterEach
    void verifyAndReset() {
        Mockito.verifyNoMoreInteractions(studentAnswerService);
        Mockito.reset(studentAnswerService);
    }

    @Test
    void addStudentAnswer() throws Exception {
        Mockito.doNothing().doThrow(new IllegalStateException())
                .when(studentAnswerService).addStudentAnswer(Mockito.any(TestDto.class), Mockito.anyInt());

        mockMvc.perform(MockMvcRequestBuilders.post("/startTest/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(createTestDto()))
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
        ;

        Mockito.verify(studentAnswerService, Mockito.times(1))
                .addStudentAnswer(Mockito.any(TestDto.class), Mockito.anyInt());
    }

    @Test
    void findStudentAnswerById() throws Exception {
        Mockito.when(studentAnswerService.findStudentAnswerById(Mockito.anyInt()))
                .thenReturn(Arrays.asList(createStudentAnswer(1), createStudentAnswer(1)));

        mockMvc.perform(MockMvcRequestBuilders.get("/startTest/{id}", 1)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].studentAnswerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].testId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].questionItemId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].studentAnswer", Matchers.is(true)))
        ;

        Mockito.verify(studentAnswerService, Mockito.times(1))
                .findStudentAnswerById(Mockito.anyInt());
    }

    private TestDto createTestDto() {
        TestDto testDto = new TestDto();
        testDto.setQuestions(createQuestion());
        testDto.setIdTests(1);
        return testDto;
    }

    private List<Question> createQuestion() {
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        question.setQuestionItems(createQuestionItem());
        question.setQuestionName("new question");
        questions.add(question);
        return questions;
    }

    private List<QuestionItem> createQuestionItem() {
        List<QuestionItem> questionItems = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            QuestionItem questionItem = new QuestionItem();
            questionItem.setAnswer(true);
            questionItem.setQuestionItemId(1);
            questionItem.setDescription("new question item" + i);
            questionItems.add(questionItem);
        }
        return questionItems;
    }

    private StudentAnswer createStudentAnswer(int index) {

        StudentAnswer studentAnswer = new StudentAnswer();
        studentAnswer.setQuestionItemId(index);
        studentAnswer.setTestId(index);
        studentAnswer.setStudentId(index);
        studentAnswer.setStudentAnswer(true);
        studentAnswer.setStudentAnswerId(index);
        return studentAnswer;
    }
}