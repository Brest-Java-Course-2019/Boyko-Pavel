package com.epam.brest.testing.dao;

import com.epam.brest.testing.model.Subject;
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

import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-db.xml", "classpath:test-dao.xml"})
@Rollback
@Transactional
class TestingDaoJpaImplTest {

    private static final int ID_SUBJECT = 1;
    private static final String CHEMISTRY = "chemistry";
    private static final String MUSIC = "music";

    @Autowired
    private TestingDao testingDao;


    @Test
    void findAll() {
        Stream<Subject> subjects = testingDao.findall();
        assertNotNull(subjects);
    }

    @Test
    void findById() {
        Subject subject = testingDao.findById(ID_SUBJECT).get();
        assertNotNull(subject);
        assertEquals(new Integer(1), subject.getIdSubject());
        assertEquals("math", subject.getSubjectName());
    }

    @Test
    void create() {
        Stream<Subject> countIdBeforeInsert = testingDao.findall();

        Subject subject = new Subject();
        subject.setSubjectName(CHEMISTRY);
        Subject subjectAfterAddInDB = testingDao.create(subject).get();
        assertNotNull(subjectAfterAddInDB);
        /**
         * check duplicate subject
         */
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testingDao.create(subject);
        });

        Stream<Subject> countIdAfterInsert = testingDao.findall();
        assertEquals(1, countIdAfterInsert.count() - countIdBeforeInsert.count());
    }

    @Test
    void update() {
        Subject subject = new Subject();
        subject.setSubjectName(MUSIC);
        Subject subjectNew = testingDao.create(subject).get();
        assertNotNull(subjectNew.getIdSubject());
        subjectNew.setSubjectName(MUSIC + "_update");
        testingDao.update(subjectNew);
        Subject subjectUpdate = testingDao.findById(subjectNew.getIdSubject()).get();
        assertEquals(MUSIC + "_update", subjectUpdate.getSubjectName());
    }

    @Test
    void delete() {
        Stream<Subject> stream = testingDao.findall();
        Subject subject = stream.findFirst().get();
//        LOGGER.info("{}", subject.getIdSubject());
        testingDao.delete(subject.getIdSubject());

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            testingDao.findById(subject.getIdSubject());
        });
    }

}