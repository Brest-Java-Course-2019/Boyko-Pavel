package com.epam.brest.project.web_app;


import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.model.Teacher;
import org.hamcrest.Matchers;
import org.junit.Ignore;
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
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/root-context.xml"})
class TeacherControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    Teacher createTeacher(){
        Teacher teacher = new Teacher();
        teacher.setLogin("admin1");
        teacher.setPassword("1");
        teacher.setTeacherId(1);
        return teacher;
    }

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    void getTestDtoAfterUnCorrectLogin() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.post("/teacher")
                        .param("login", "admin41")
                        .param("password", "1")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("<td>Physics</td>")))
        ;
    }

    @Test
    void getTestDtoAfterCorrectLogin() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.post("/teacher")
                        .param("login", "admin1")
                        .param("password", "1")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/teacher"))
        ;
    }


    @Test
    void gotToTestTeacher() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/teacher").sessionAttr("teacher", createTeacher())
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("<td>2019-04-05</td>")))
        ;
    }


    @Test
    void deleteTestById() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/teacher/1/delete")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/teacher"))
        ;
    }

}