package com.epam.brest.project.rest_app;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.model.Question;
import com.epam.brest.project.model.QuestionItem;
import com.epam.brest.project.model.Teacher;
import com.epam.brest.project.service.TeacherService;
import com.epam.brest.project.service.TestDtoService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
class TestDtoRestControllerTest {

    @Autowired
    private TestDtoRestController testDtoRestController ;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TestDtoService testDtoService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(testDtoRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @AfterEach
    void verifyAndReset() {
        Mockito.verifyNoMoreInteractions(testDtoService);
        Mockito.reset(testDtoService);
    }


    @Test
    void findTestDtoById() throws Exception {
        Mockito.when(testDtoService.findTestDtoById(Mockito.anyInt()))
                .thenReturn(createTestDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/teacher/editTest/{id}", 1)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.testName", Matchers.is("Algebra")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subjectName", Matchers.is("Math")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.questions[0].questionName", Matchers.is("new question")))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.questions[0].questionItems.[2].description", Matchers.is("questionItem_2")))

        ;

        Mockito.verify(testDtoService, Mockito.times(1))
                .findTestDtoById(Mockito.anyInt());
    }

    @Test
    void addTestDto() throws Exception {
        Mockito.doNothing().doThrow(new IllegalStateException())
                .when(testDtoService).addTestDto(Mockito.any(TestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/teacher/editTest")
                .content(mapper.writeValueAsString(createTestDto()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
        ;
        Mockito.verify(testDtoService, Mockito.times(1))
                .addTestDto(Mockito.any(TestDto.class));
    }

    @Test
    void deleteTestDto() throws Exception {
        Mockito.doNothing().doThrow(new IllegalStateException())
                .when(testDtoService).deleteTestDto(Mockito.anyInt());

        mockMvc.perform(MockMvcRequestBuilders.delete("/teacher/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
        ;
        Mockito.verify(testDtoService, Mockito.times(1))
                .deleteTestDto(Mockito.anyInt());
    }


    @Test
    void updateTestDto() throws Exception {
        Mockito.doNothing().doThrow(new IllegalStateException())
                .when(testDtoService).updateTestDto(Mockito.any(TestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.put("/teacher/editTest")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(createTestDto()))
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
        ;
        Mockito.verify(testDtoService, Mockito.times(1))
                .updateTestDto(Mockito.any(TestDto.class));
    }



    private TestDto createTestDto() {
        TestDto testDto = new TestDto();
        testDto.setTestName("Algebra");
        testDto.setIdTests(1);
        testDto.setTeacherId(1);
        testDto.setSubjectName("Math");
        testDto.setQuestions(createQuestions());
        testDto.setSubjectId(1);
        return testDto;
    }

    private List<Question> createQuestions() {
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        question.setQuestionName("new question");
        question.setQuestionId(1);
        question.setQuestionItems(createQuestionItems());
        questions.add(question);
        return questions;
    }

    private List<QuestionItem> createQuestionItems(){
        List<QuestionItem> questionItems = new ArrayList<>();
        for (int i = 0; i <4 ; i++) {
            QuestionItem questionItem = new QuestionItem();
            questionItem.setDescription("questionItem_"+ i);
            questionItem.setQuestionItemId(i);
            questionItem.setAnswer(true);
            questionItems.add(questionItem);
        }
        return  questionItems;
    }
}