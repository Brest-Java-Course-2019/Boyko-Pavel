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
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-dao.xml"})
@Transactional
@Rollback
class SubjectDaoJpaImplTest {

    private static final int ID_SUBJECT = 1;
    private static final String CHEMISTRY = "chemistry";
    private static final String MUSIC = "music";

    @Autowired
    private SubjectDao subjectDao;


    @Test
    void findAll() {
        Stream<Subject> subjects = subjectDao.findall();
        assertNotNull(subjects);
    }

    @Test
    void findById() {
        Subject subject = subjectDao.findById(ID_SUBJECT).get();
        assertNotNull(subject);
        assertEquals(new Integer(1), subject.getIdSubject());
//        assertEquals("math", subject.getSubjectName());
    }

    @Test
    void create() {
        Stream<Subject> countIdBeforeInsert = subjectDao.findall();

        Subject subject = new Subject();
        subject.setSubjectName(CHEMISTRY);

//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            subjectDao.add(subject);
//        });

        Stream<Subject> countIdAfterInsert = subjectDao.findall();
        assertEquals(1, countIdAfterInsert.count() - countIdBeforeInsert.count());
    }

    @Test
    void update() {
        Subject subject = new Subject();
        subject.setSubjectName(MUSIC);
        Subject subjectNew = subjectDao.add(subject).get();
        assertNotNull(subjectNew.getIdSubject());
        subjectNew.setSubjectName(MUSIC + "_update");
        subjectDao.update(subjectNew);
        Subject subjectUpdate = subjectDao.findById(subjectNew.getIdSubject()).get();
        assertEquals(MUSIC + "_update", subjectUpdate.getSubjectName());
    }

    @Test
    void delete() {
        Stream<Subject> stream = subjectDao.findall();
        Subject subject = stream.findFirst().get();
        subjectDao.delete(subject.getIdSubject());
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            subjectDao.findById(subject.getIdSubject());
        });
    }

}