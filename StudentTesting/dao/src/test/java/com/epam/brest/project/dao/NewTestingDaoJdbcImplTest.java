//package com.epam.brest.project.dao;
//
//import com.epam.brest.project.DTO.NewTestDTO;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath*:dao-context.xml"})
//@Transactional
//@Rollback
//class NewTestingDaoJdbcImplTest {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectDaoJdbcImpl.class);
//    @Autowired
//    NewTestingDaoJdbcImpl newTestingDaoJdbc;
//
//    @Test
//    void findall() {
//        Stream<NewTestDTO> newTestDTOStream = newTestingDaoJdbc.findall();
//        assertNotNull(newTestDTOStream);
////        assertEquals(2, newTestDTOStream.count());
//        for (Object a : newTestDTOStream.toArray()) {
//            LOGGER.info("_____________________________{}______________", a);
//
//        }
//
//    }
//
//    @Test
//    void findById() {
//    }
//
//    @Test
//    void add() {
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void delete() {
//    }
//}