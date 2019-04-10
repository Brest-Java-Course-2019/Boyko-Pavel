package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.model.Test;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * TestDao interface.
 */
public interface TestDao {


    /**
     * Find all Test.
     *
     * @return Test stream.
     */
    Stream<Test> findAll();

    /**
     * Find all Test by id.
     *
     * @param id Test id.
     * @return Test.
     */
    Optional<Test> findById(final Integer id);

    /**
     * Find all TestDto by id.
     *
     * @param id TestDto id.
     * @return TestDto.
     */
    Optional<TestDto> findTestDtoById(final Integer id);

    /**
     * Add new Test.
     *
     * @param test new Test to add.
     * @return new Test.
     */
    Optional<Test> add(final Test test);

    /**
     * Update Test.
     *
     * @param test Test to update.
     */
    void update(final Test test);

    /**
     * Delete Test.
     *
     * @param id Test id.
     */
    void delete(final int id);
}
