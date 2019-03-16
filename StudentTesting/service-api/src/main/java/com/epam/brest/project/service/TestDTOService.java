package com.epam.brest.project.service;

import com.epam.brest.project.DTO.TestDTO;
import com.epam.brest.project.model.Subject;

import java.util.stream.Stream;

public interface TestDTOService {

    TestDTO findTestDtoById(Integer id);

    Stream<Subject> findAllSubject();

}
