package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.model.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath*:dao-context.xml"})
@Transactional
@Rollback
class TeacherDaoImplTestDao {

    @Autowired
    private TeacherDao studentTestDTODao;

    @Test
    void findAllDtoTeacherByID() {
        Teacher teacher = studentTestDTODao.findTeacherByLogin("admin2").get();
        assertEquals("2", teacher.getPassword());
        Stream<StudentTestDto> studentTestDTOList = studentTestDTODao.findAllDtoTeacher(teacher.getTeacherId());
        assertEquals(1, studentTestDTOList.count());
    }

    @Test
    void wrongLogin() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            studentTestDTODao.findTeacherByLogin("adm4in");
        });
    }

    @Test
    void correctLogin() {
        Teacher teacher = studentTestDTODao.findTeacherByLogin("admin1").get();
        assertEquals("admin1", teacher.getLogin());
    }
}