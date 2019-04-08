package com.epam.brest.project.rest_app;

import com.epam.brest.project.model.Subject;
import com.epam.brest.project.service.SubjectService;
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
class SubjectRestControllerTest {

    @Autowired
    private SubjectRestController subjectRestController;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private SubjectService subjectService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(subjectRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @AfterEach
    void verifyAndReset() {
        Mockito.verifyNoMoreInteractions(subjectService);
        Mockito.reset(subjectService);
    }


    @Test
    void findAllSubject() throws Exception {
        Mockito.when(subjectService.findAll())
                .thenReturn(Arrays.asList(createSubject(1), createSubject(2)));

        mockMvc.perform(MockMvcRequestBuilders.get("/subject")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].subjectId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Math1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].subjectId", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Math2")))

        ;

        Mockito.verify(subjectService, Mockito.times(1))
                .findAll();
    }

    private Subject createSubject(int index) {
        Subject subject = new Subject();
        subject.setName("Math" + index);
        subject.setSubjectId(index);
        return subject;
    }
}