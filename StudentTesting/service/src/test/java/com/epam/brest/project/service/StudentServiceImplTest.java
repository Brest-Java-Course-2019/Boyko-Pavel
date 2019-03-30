package com.epam.brest.project.service;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.dao.QuestionItemDao;
import com.epam.brest.project.model.QuestionItem;
import com.epam.brest.project.model.StudentAnswer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml", "classpath*:dao-context.xml"})
class StudentServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);
    private static final int STUDENT_ID = 1;

    @Autowired
    private QuestionItemDao questionItemDao;

    @Autowired
    private StudentService studentService;


    @Test
    void findAllDto() {
        List<StudentTestDto> studentTestDtos = studentService.findAllDto();
        assertEquals(3, studentTestDtos.size());
    }
}