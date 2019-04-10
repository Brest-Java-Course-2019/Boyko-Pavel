package com.epam.brest.project.web_app;

import com.epam.brest.project.builder.DateBuilder;
import com.epam.brest.project.model.Student;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:web_app_test.xml"})
class StartTestControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @AfterEach
    void clear(){
        mockMvc = null;
    }
    private Student createStudent() {
        Student teacher = new Student();
        teacher.setStudentId(1);
        teacher.setPassword("1");
        teacher.setLogin("1");
        return teacher;
    }


    @Test
    void findStudentTestByStudentId() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/student").sessionAttr("student", createStudent())
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("<td>Optics</td>")))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("<td>Probability theory</td>")))
        ;
    }


    @Ignore
    void goToSolveTrainingTest() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/student/startTest/1")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("id=\"questions0.questionItems3.description\" name=\"questions[0].questionItems[3].description\">ANSWER: 5</textarea>")))
        ;
    }
}