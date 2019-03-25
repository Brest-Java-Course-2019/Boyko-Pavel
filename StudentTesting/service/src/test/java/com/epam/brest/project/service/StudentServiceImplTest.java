package com.epam.brest.project.service;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.model.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml", "classpath*:dao-context.xml"})
class StudentServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentService studentService;

    @Test
    void findAllDto() {
        List<StudentTestDto> studentTestDtos = studentService.findAllDto();
        assertEquals(3, studentTestDtos.size());
    }

    @Test
    void findTeacherByLogin(){
        Teacher teacher  = studentService.findTeacherByLogin("admin1");
        assertEquals(new Integer(1), teacher.getTeacherId());
    }

//    @Test
//    void findTeacherByWrongLogin(){
//        Assertions.assertThrows(EmptyResultDataAccessException.class, () ->
//                studentService.findTeacherByLogin("Admin4"));
//    }
    @Test
    void findAllTestDtoTeacher() {
        Teacher teacher  = studentService.findTeacherByLogin("admin1");
        List<StudentTestDto> studentTestDto = studentService.findAllDtoTestTeacher(teacher.getTeacherId());
        assertEquals(2, studentTestDto.size());
//        assertEquals("Algebra", studentTestDto.get(0).getTestName());
    }
}