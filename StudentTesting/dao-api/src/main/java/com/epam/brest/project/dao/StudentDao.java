package com.epam.brest.project.dao;
import com.epam.brest.project.model.Student;


import java.util.Optional;
import java.util.stream.Stream;

public interface StudentDao {

    Stream<Student> findAll();

    Optional<Student> findById(final Integer id);

    Optional<Student> add(final Student student);

}
