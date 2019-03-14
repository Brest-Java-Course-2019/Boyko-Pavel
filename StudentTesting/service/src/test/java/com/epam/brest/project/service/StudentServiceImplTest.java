package com.epam.brest.project.service;

import com.epam.brest.project.DTO.StudentTestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml", "classpath*:dao-context.xml"})
class StudentServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentService studentService;

    @Test
    void findAllDto() {
        List<StudentTestDTO> studentTestDTOS = studentService.findAllDto();
        assertEquals(2, studentTestDTOS.size());
    }
}