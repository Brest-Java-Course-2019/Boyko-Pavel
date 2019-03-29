package com.epam.brest.project.dao;

import com.epam.brest.project.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath*:dao-context.xml"})
@Transactional
@Rollback
class StudentDaoImplTest {

    private final static int ID = 1;
    private static final String NEW_LAST_NAME = "lol";
    private static final String NEW_FIRST_NAME = "kek";

    @Autowired
    private StudentDao studentDao;

    @Test
    void findAll() {
        List<Student> students = studentDao.findAll().collect(Collectors.toList());
        assertEquals("petr", students.get(1).getName());
        assertNotNull(students);
    }

    @Test
    void findByIdStudent() {
        Student student = studentDao.findById(ID).get();
        assertEquals("kolya", student.getName());
    }

    @Test
    void add() {
        Student student = new Student();
        student.setName("Pasha");
        student.setSurname("Phoen");
        studentDao.add(student);
        Student studentAfterAdd = studentDao.findById(4).get();
        assertEquals(student.getName(), studentAfterAdd.getName());
    }
}