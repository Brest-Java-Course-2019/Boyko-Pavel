package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.StudentTestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath*:dao-context.xml"})
@Transactional
@Rollback
class StudentTestDTODaoImplTestDao {

    @Autowired
    private StudentTestDao studentTestDao;

    @Test
    void findall() {
        Stream<StudentTestDTO> studentTestDTOList = studentTestDao.findallDto();
        assertEquals(2, studentTestDTOList.count());
    }
}