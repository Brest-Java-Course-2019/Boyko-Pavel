package com.epam.brest.project.dao;

import com.epam.brest.project.model.Student;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * StudentDao interface.
 */
public interface StudentDao {

    /**
     * Find all Student.
     *
     * @return student stream.
     */
    Stream<Student> findAll();

    /**
     * Find Student by login.
     *
     * @param login Student login.
     * @return Student.
     */
    Optional<Student> findByLogin(final String login);

    /**
     * Add new Student.
     *
     * @param student new Student to add.
     * @return new Student.
     */
    Optional<Student> add(final Student student);

}
