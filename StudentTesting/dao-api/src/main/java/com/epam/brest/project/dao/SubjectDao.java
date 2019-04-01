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
    Stream<Subject> findall();

    /**
     * Find Subject by id.
     *
     * @param id subject.
     * @return subject.
     */
    Optional<Subject> findById(final Integer id);

    /**
     * Add new Subject.
     *
     * @param subject new Subject.
     */
    Optional<Subject> add(final Subject subject);

    /**
     * Update Subject.
     *
     * @param subject Subject for updating.
     */
    void update(final Subject subject);

    /**
     * Delete subject.
     *
     * @param id id Subject for deleting.
     */
    void delete(final int id);
}
