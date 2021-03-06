package com.epam.brest.project.service;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.model.Teacher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml", "classpath*:dao-context.xml"})
class TeacherServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private TeacherService teacherService;

    @Test
    void findTeacherByLogin() {
        Teacher teacherFromForm = new Teacher();
        teacherFromForm.setLogin("admin1");
        Teacher teacher = teacherService.findTeacherByLogin(teacherFromForm);
        assertEquals(new Integer(1), teacher.getTeacherId());
    }

    @Test
    void findByUnCorrectLogin() {
        Teacher teacherFromForm = new Teacher();
        teacherFromForm.setLogin("admin11");
        assertNull(teacherService.findTeacherByLogin(teacherFromForm));
    }

    @Test
    void findAllTestDto() {
        List<StudentTestDto> teacher = teacherService.findAllDtoTestTeacher(1);
        assertEquals(2, teacher.size());
    }

}