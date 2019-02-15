package com.epam.brest.testing.dao;

import com.epam.brest.testing.model.Subject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-db.xml", "classpath:test-dao.xml"})
@Rollback
@Transactional
class TestingDaoJpaImplTest {

    @Autowired
    TestingDao testingDao;

    @Test
    void findAll() {
        List<Subject> departments = testingDao.findall().collect(Collectors.toList());
        assertFalse(departments.isEmpty());
    }

}