package com.epam.brest.project.rest_app;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.model.Teacher;
import com.epam.brest.project.service.TeacherService;
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
class TeacherRestControllerTest {

    @Autowired
    private TeacherRestController teacherRestController;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TeacherService teacherService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(teacherRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @AfterEach
    void verifyAndReset() {
        Mockito.verifyNoMoreInteractions(teacherService);
        Mockito.reset(teacherService);
    }


    @Test
    void findAllDtoTestTeacher() throws Exception {
        Mockito.when(teacherService.findAllDtoTestTeacher(Mockito.anyInt()))
                .thenReturn(Arrays.asList(createStudentTestDto(1), createStudentTestDto(2)));

        mockMvc.perform(MockMvcRequestBuilders.get("/teacher/1")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].testName", Matchers.is("algebra1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].subjectName", Matchers.is("math1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].countQuestion", Matchers.is(2)))

        ;

        Mockito.verify(teacherService, Mockito.times(1))
                .findAllDtoTestTeacher(Mockito.anyInt());
    }

    @Test
    void findTeacherByLogin() throws Exception {
        Mockito.when(teacherService.findTeacherByLogin(Mockito.any(Teacher.class)))
                .thenReturn(teacherCreator());

        Teacher teacher = new Teacher();
        teacher.setLogin("admin1");

        mockMvc.perform(MockMvcRequestBuilders.post("/teacher")
                .content(mapper.writeValueAsString(teacher))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("1")))
        ;
        Mockito.verify(teacherService, Mockito.times(1))
                .findTeacherByLogin(Mockito.any(Teacher.class));
    }

    private StudentTestDto createStudentTestDto(int index) {
        StudentTestDto studentTestDto = new StudentTestDto();
        studentTestDto.setCountQuestion(index);
        studentTestDto.setIdTests(index);
        studentTestDto.setSubjectName("math" + index);
        studentTestDto.setTestName("algebra" + index);
        return studentTestDto;
    }

    private Teacher teacherCreator() {
        Teacher teacher = new Teacher();
        teacher.setLogin("admin1");
        teacher.setPassword("1");
        teacher.setTeacherId(1);
        return teacher;
    }
}