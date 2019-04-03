package com.epam.brest.project.dao;

import com.epam.brest.project.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath*:dao-context.xml"})
@Transactional
@Rollback
class StudentDaoImplTest {


    @Autowired
    private StudentDao studentDao;

    @Test
    void findAll() {
        Student students = studentDao.findAll().findFirst().get();
        assertEquals("kolya", students.getName());
        assertNotNull(students);
    }

    @Test
    void findByLoginStudent() {
        Student student = studentDao.findByLogin("1").get();
        assertEquals("kolya", student.getName());
    }

    @Test
    void add() {
        Student student = new Student();
        student.setName("Pasha");
        student.setSurname("Phoen");
        student.setLogin("33");
        student.setPassword("33");
        studentDao.add(student);
        assertNotNull(studentDao.findByLogin("33").get());
    }
}