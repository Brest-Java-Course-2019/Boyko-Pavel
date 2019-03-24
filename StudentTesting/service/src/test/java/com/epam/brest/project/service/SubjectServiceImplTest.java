package com.epam.brest.project.service;

import com.epam.brest.project.model.Subject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml", "classpath*:dao-context.xml"})
class SubjectServiceImplTest {

    @Autowired
    private SubjectService subjectService;

    @Test
    void findAllSubject() {
        List<Subject> subject = subjectService.findAll();
        assertEquals(3, subject.size());
    }
}