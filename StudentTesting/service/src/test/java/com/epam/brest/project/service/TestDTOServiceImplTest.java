package com.epam.brest.project.service;

import com.epam.brest.project.DTO.TestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml", "classpath*:dao-context.xml"})
class TestDTOServiceImplTest {

    @Autowired
    private TestDTOService testDTOService;

    @Test
    void findTestDtoById() {
        TestDTO testDTO = testDTOService.findTestDtoById(1);
        assertEquals("Algebra", testDTO.getTestName());
        assertEquals("Math", testDTO.getSubjectName());
        assertEquals("Count 2+2=", testDTO.getQuestions().get(0).getQuestionName());
        assertEquals("ANSWER: 4", testDTO.getQuestionItems().get(0).get(2).getDescription());
    }
}