package com.epam.brest.project.service;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.builder.DateBuilder;
import com.epam.brest.project.dao.QuestionItemDao;
import com.epam.brest.project.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
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

    private DateBuilder getDateBuilder() {
        DateBuilder dateBuilder = new DateBuilder();
        dateBuilder.setStartDate("2002-10-20");
        dateBuilder.setEndDate("2019-02-06");
        return dateBuilder;
    }

    @Test
    void findAllDto() {
        List<StudentTestDto> studentTestDtos = studentService.findAllDto();
        assertEquals(3, studentTestDtos.size());
    }

    @Test
    void findFilteredDto() throws ParseException {
        List<StudentTestDto> studentTestDtos = studentService.filterByDate(getDateBuilder(), 1);
        assertEquals(0, studentTestDtos.size());
    }

    @Test
    void findTestDtoById(){
        List<StudentTestDto> studentTestDtos = studentService.findAllDtoTestStudent(1);
        assertEquals(2, studentTestDtos.size());
    }

    @Test
    void findStudentById(){
        Student student = studentService.findStudentByLogin("1");
        assertEquals("kolya", student.getName());
    }
}