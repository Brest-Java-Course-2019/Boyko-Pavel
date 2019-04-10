package com.epam.brest.project.dao;

import com.epam.brest.project.model.Subject;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * SubjectDao interface.
 */
public interface SubjectDao {

    /**
     * Find all Subject.
     *
     * @return Subject stream.
     */
    Stream<Subject> findAll();

    /**
     * Find Subject by id.
     *
     * @param id Subject.
     * @return Subject.
     */
    Optional<Subject> findById(final Integer id);

    /**
     * Add new Subject.
     *
     * @param subject new Subject.
     * @return Subject
     */
    Optional<Subject> add(final Subject subject);

    /**
     * Update Subject.
     *
     * @param subject Subject for updating.
     */
    void update(final Subject subject);

    /**
     * Delete Subject.
     *
     * @param id id Subject.
     */
    void delete(final int id);
}
