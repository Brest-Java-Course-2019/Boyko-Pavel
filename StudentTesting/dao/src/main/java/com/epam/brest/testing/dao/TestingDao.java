package com.epam.brest.testing.dao;
import com.epam.brest.testing.model.Subject;

import java.util.Optional;
import java.util.stream.Stream;

public interface TestingDao {

    Stream<Subject> findall();

    Optional<Subject> findById(final Integer id);

    int create(final Subject department);

    void update(final Subject department);

    void delete(final Integer id);
}
