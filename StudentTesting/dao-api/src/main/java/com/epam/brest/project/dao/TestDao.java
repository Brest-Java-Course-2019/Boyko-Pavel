package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.TestDTO;
import com.epam.brest.project.model.Test;

import java.util.Optional;
import java.util.stream.Stream;

public interface TestDao {

    Stream<Test> findall();

    Optional<Test> findById(final Integer id);

    Optional<TestDTO> findTestDTOById(final Integer id);

    Optional<Test> add(final Test test);

    void update(final Test test);

    void delete(final int id);
}
