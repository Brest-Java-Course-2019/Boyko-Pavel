package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.model.Test;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * TestDao Interface.
 */
public interface TestDao {


    /**
     * Find all test.
     *
     * @return test stream.
     */
    Stream<Test> findAll();

    /**
     * Find all test by id.
     *
     * @param id test id.
     * @return test.
     */
    Optional<Test> findById(final Integer id);

    /**
     * Find all testDto by id.
     *
     * @param id testDto id.
     * @return testDto.
     */
    Optional<TestDto> findTestDtoById(final Integer id);

    /**
     * Add new test.
     *
     * @param test new test.
     * @return new test
     */
    Optional<Test> add(final Test test);

    /**
     * Update test.
     *
     * @param test test to update.
     */
    void update(final Test test);

    /**
     * Delete test.
     *
     * @param id test id.
     */
    void delete(final int id);
}
