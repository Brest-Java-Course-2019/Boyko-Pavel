package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.model.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath*:dao-context.xml"})
@Transactional
@Rollback
class TestDaoJdbcImplTest {

    private static final int TEST_ID = 1;
    private static final int DELETED_TEST_ID = 4;
    private static final int SUBJECT_ID_UNCORRECT = 55;

    @Autowired
    private TestDao testDao;

    @org.junit.jupiter.api.Test
    void findAllTests() {
        Stream<Test> testStream = testDao.findAll();
        assertNotNull(testStream);
        assertEquals(3, testStream.count());
    }

    @org.junit.jupiter.api.Test
    void findTestByID() {
        Test test = testDao.findById(TEST_ID).get();
        assertEquals("Algebra", test.getName());
    }

    @org.junit.jupiter.api.Test
    void findTestDTOByID() {
        TestDto testDTO = testDao.findTestDtoById(TEST_ID).get();
        assertEquals("Math", testDTO.getSubjectName());
    }

    @org.junit.jupiter.api.Test
    void findTestByDeletedID() {
        Assertions.assertThrows(DataAccessException.class, () -> {
            testDao.findById(DELETED_TEST_ID);
        });
    }

    @org.junit.jupiter.api.Test
    void addQuestion() {
        Stream<Test> countIdBeforeInsert = testDao.findAll();
        Test test = new Test();
        test.setName("Trigonometry");
        test.setTeacherId(2);
        test.setSubjectId(2);
        testDao.add(test);
        Stream<Test> countIdAfterInsert = testDao.findAll();

        assertEquals(1, countIdAfterInsert.count() - countIdBeforeInsert.count());
        assertEquals(new Integer(5), test.getTestId());
    }

    @org.junit.jupiter.api.Test
    void addWrongQuestion() {
        Stream<Test> countIdBeforeInsert = testDao.findAll();
        Test test = new Test();
        test.setName("Trigonometry");
        test.setTeacherId(2);
        test.setSubjectId(SUBJECT_ID_UNCORRECT);
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            testDao.add(test);
        });
    }


    @org.junit.jupiter.api.Test
    void updateQuestion() {
        Test test = testDao.findById(TEST_ID).get();
        String testBeforeUpdate = test.getName();
        test.setName(testBeforeUpdate + "_update");
        testDao.update(test);
        Test testAfterUpdate = testDao.findById(TEST_ID).get();
        assertEquals(test.getName(), testAfterUpdate.getName());
    }


    @org.junit.jupiter.api.Test
    void deleteQuestionByID() {
        testDao.delete(TEST_ID);
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            testDao.findById(TEST_ID);
        });
    }

    @org.junit.jupiter.api.Test
    void deleteQuestionNotExistID() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            testDao.delete(34343434);
        });
    }
}