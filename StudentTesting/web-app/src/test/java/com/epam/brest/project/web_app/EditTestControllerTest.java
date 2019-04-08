package com.epam.brest.project.web_app;


import com.epam.brest.project.model.Teacher;
import org.hamcrest.Matchers;
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
class EditTestControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }


    private Teacher createTeacher() {
        Teacher teacher = new Teacher();
        teacher.setPassword("1");
        teacher.setLogin("admin1");
        return teacher;
    }

    @Test
    void addNewTest() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.post("/editTest").sessionAttr("teacher", createTeacher())
                        .sessionAttr("teacher", createTeacher())
                        .param("subjectId", "1")
                        .param("testName", "newTest")
                        .param("teacherId", "1")
                        .param("newQuestion", "newQuestion")
                        .param("newAnswer", "1", "0", "0", "0")
                        .param("newDescription", "newDesc1", "newDesc2", "newDesc3", "newDesc4")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/teacher"))
        ;
    }

    @Test
    void createNewTest() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/editTest").sessionAttr("teacher", createTeacher())
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("id=\"testName\" name=\"testName\" value=\"\"/>")))
        ;
    }
}