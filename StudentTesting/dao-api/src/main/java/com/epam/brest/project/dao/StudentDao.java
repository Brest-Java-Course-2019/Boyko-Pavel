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
     * Find student by id.
     *
     * @param id student id.
     * @return student.
     */
    Optional<Student> findById(final Integer id);

    /**
     * Add new student.
     *
     * @param student new student.
     * @return new student.
     */
    Optional<Student> add(final Student student);

}
