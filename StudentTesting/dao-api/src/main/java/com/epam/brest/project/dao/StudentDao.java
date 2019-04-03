package com.epam.brest.project.dao;

import com.epam.brest.project.model.Student;

import java.util.Optional;
import java.util.stream.Stream;


/**
 * Student Dao interface.
 */
public interface StudentDao {

    /**
     * Find all student.
     *
     * @return student stream.
     */
    Stream<Student> findAll();

    /**
     * Find student by login.
     *
     * @param login student login.
     * @return student.
     */
    Optional<Student> findByLogin(final String login);

    /**
     * Add new student.
     *
     * @param student new student.
     * @return new student.
     */
    Optional<Student> add(final Student student);

}
