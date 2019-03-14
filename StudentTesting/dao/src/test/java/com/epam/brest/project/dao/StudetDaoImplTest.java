//package com.epam.brest.project.dao;
//
//import com.epam.brest.project.model.Student;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.stream.Stream;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath*:dao-context.xml"})
//@Transactional
//@Rollback
//class StudetDaoImplTest {
//
//    private final static int ID = 1;
//    private static final String NEW_LAST_NAME = "lol";
//    private static final String NEW_FIRST_NAME = "kek";
//
//    @Autowired
//    private StudentDao studetDao;
//
//    @Test
//    void findall() {
//        Stream<Student> subjects = studetDao.findall();
//        assertNotNull(subjects);
//    }
//
//    @Test
//    void findById() {
//        Student student = studetDao.findById(ID).get();
//        assertEquals(new Integer("1"), student.getStudentId());
//    }
//
//    @Test
//    void add() {
//
//
//    }
//
//    @Test
//    void update() {
//        Student student = new Student();
//        student.setSurname(NEW_LAST_NAME);
//        student.setName(NEW_FIRST_NAME);
//        Student studentNew = studetDao.add(student).get();
//        studentNew.setName(NEW_FIRST_NAME + "__new");
//        studentNew.setSurname(NEW_FIRST_NAME + "__new");
//        studetDao.update(student);
//        Student studentUpdate = studetDao.findById(student.getStudentId()).get();
//        assertEquals("kek__new",studentUpdate.getName());
//    }
//
//    @Test
//    void delete() {
//    }
//}