package com.epam.brest.project.dao;

import com.epam.brest.project.model.Subject;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath*:dao-context.xml"})
@Transactional
@Rollback
class SubjectDaoJdbcImplTest {

    private static final int ID_SUBJECT = 1;
    private static final String CHEMISTRY = "chemistry";
    private static final String MATH = "math";
    private static final String MUSIC = "music";

    @Autowired
    private SubjectDao subjectDao;


    @Test
    void findAll() {
        Stream<Subject> subjects = subjectDao.findall();
        assertNotNull(subjects);
        assertEquals(3, subjects.count());
    }

    @Test
    void findById() {
        Subject subject = subjectDao.findById(ID_SUBJECT).get();
        assertNotNull(subject);
        assertEquals(new Integer(1), subject.getSubjectId());
        assertEquals("Biology", subject.getName());
    }

    @Test
    void add() {
        Stream<Subject> countIdBeforeInsert = subjectDao.findall();
        Subject subject = new Subject();
        subject.setName(CHEMISTRY);
        Subject newDepartment = subjectDao.add(subject).get();
        assertNotNull(newDepartment.getSubjectId());
        Stream<Subject> countIdAfterInsert = subjectDao.findall();
        assertEquals(1, countIdAfterInsert.count() - countIdBeforeInsert.count());
    }

    @Test
    void update() {
        Subject subject = new Subject();
        subject.setName(MUSIC);
        Subject subjectNew = subjectDao.add(subject).get();
        assertNotNull(subjectNew.getSubjectId());
        subjectNew.setName(MUSIC + "_update");
        subjectDao.update(subjectNew);
        Subject subjectUpdate = subjectDao.findById(subjectNew.getSubjectId()).get();
        assertEquals(MUSIC + "_update", subjectUpdate.getName());
    }

    @Test
    void delete() {
        Stream<Subject> stream = subjectDao.findall();
        Subject subject = stream.findFirst().get();
        subjectDao.delete(subject.getSubjectId());
        Assertions.assertThrows(EmptyResultDataAccessException.class, () ->
                subjectDao.findById(subject.getSubjectId()));
    }

}