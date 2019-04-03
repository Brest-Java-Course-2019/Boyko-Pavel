package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.builder.DateBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath*:dao-context.xml"})
@Transactional
@Rollback
class StudentTestDTODaoImplTestDao {

    @Autowired
    private StudentTestDtoDao studentTestDTODao;

    DateBuilder getdateBuilder() throws ParseException {
        DateBuilder dateBuilder = new DateBuilder();
        dateBuilder.setStartDate("2002-10-20");
        dateBuilder.setEndDate("2019-02-06");
        return dateBuilder;
    }

    @Test
    void findAll() {
        Stream<StudentTestDto> studentTestDTOList = studentTestDTODao.findAllDto();
        assertEquals(3, studentTestDTOList.count());
    }

    @Test
    void filterByDate() throws ParseException {
        DateBuilder dateBuilder = getdateBuilder();
        Stream<StudentTestDto> studentTestDTOList1 = studentTestDTODao.filterByDate(dateBuilder, 1);
        assertEquals(0, studentTestDTOList1.count());
        Stream<StudentTestDto> studentTestDTOList2 = studentTestDTODao.filterByDate(dateBuilder, null);
        assertEquals(1, studentTestDTOList2.count());
    }


    @Test
    void findNotDoneTestStudentById() throws ParseException {
        DateBuilder dateBuilder = getdateBuilder();
        Stream<StudentTestDto> studentTestDTOList1 = studentTestDTODao.findNotDoneTestStudentById(1);
        assertEquals(2, studentTestDTOList1.count());
    }


}