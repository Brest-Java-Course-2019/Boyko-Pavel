package com.epam.brest.project.rest_app;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.builder.DateBuilder;
import com.epam.brest.project.model.Student;
import com.epam.brest.project.service.StudentService;
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

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
class StudentRestControllerTest {

    @Autowired
    private StudentRestController controller;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private StudentService studentService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @AfterEach
    void verifyAndReset() {
        Mockito.verifyNoMoreInteractions(studentService);
        Mockito.reset(studentService);
    }


    @Test
    void shouldFindAllStudentTestDto() throws Exception {
        Mockito.when(studentService.findAllDto()).thenReturn(Arrays.asList(createStudentTestDto(1),
                createStudentTestDto(2)));

        mockMvc.perform(MockMvcRequestBuilders.get("/student")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].testName", Matchers.is("algebra2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].subjectName", Matchers.is("math2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].countQuestion", Matchers.is(1)))
        ;

        Mockito.verify(studentService, Mockito.times(1)).findAllDto();
    }

    @Test
    void filterByDate() throws Exception {
        Mockito.when(studentService.filterByDate(Mockito.any(DateBuilder.class), Mockito.anyInt()))
                .thenReturn(Arrays.asList(createStudentTestDto(1), createStudentTestDto(2)));

        mockMvc.perform(MockMvcRequestBuilders.post("/student/filter/{studentId}", 1)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(createDateBuilder()))
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].testName", Matchers.is("algebra1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].subjectName", Matchers.is("math1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].countQuestion", Matchers.is(2)))
        ;

        Mockito.verify(studentService, Mockito.times(1))
                .filterByDate(Mockito.any(DateBuilder.class), Mockito.anyInt());
    }

    @Test
    void findStudentByLogin() throws Exception {
        Mockito.when(studentService.findStudentByLogin(Mockito.anyString())).thenReturn(createStudent());

        String login = "1";
        mockMvc.perform(MockMvcRequestBuilders.post("/student")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(login))
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("1")))
        ;

        Mockito.verify(studentService, Mockito.times(1))
                .findStudentByLogin(Mockito.any(String.class));
    }

    @Test
    void findAllDtoTestStudent() throws Exception {
        Mockito.when(studentService.findAllDtoTestStudent(Mockito.anyInt()))
                .thenReturn(Arrays.asList(createStudentTestDto(1), createStudentTestDto(1)));

        mockMvc.perform(MockMvcRequestBuilders.get("/student/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].testName", Matchers.is("algebra1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].subjectName", Matchers.is("math1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].countQuestion", Matchers.is(1)))
        ;

        Mockito.verify(studentService, Mockito.times(1))
                .findAllDtoTestStudent(Mockito.anyInt());
    }


    private DateBuilder createDateBuilder() {
        DateBuilder dateBuilder = new DateBuilder();
        dateBuilder.setStartDate("2018-01-01");
        dateBuilder.setEndDate("2019-01-01");
        return dateBuilder;
    }

    private Student createStudent() {
        Student student = new Student();
        student.setPassword("1");
        student.setLogin("admin1");
        student.setStudentId(1);
        return student;
    }


    private StudentTestDto createStudentTestDto(int index) {
        StudentTestDto studentTestDto = new StudentTestDto();
        studentTestDto.setCountQuestion(index);
        studentTestDto.setIdTests(index);
        studentTestDto.setSubjectName("math" + index);
        studentTestDto.setTestName("algebra" + index);
        return studentTestDto;
    }
}