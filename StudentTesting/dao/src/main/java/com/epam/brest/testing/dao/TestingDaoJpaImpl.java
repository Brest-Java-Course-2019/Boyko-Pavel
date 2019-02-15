package com.epam.brest.testing.dao;

import com.epam.brest.testing.model.Subject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TestingDaoJpaImpl implements TestingDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestingDaoJpaImpl.class);

    private static final String SELECT_SQL ="SELECT id_subject, subject_name FROM subject";

    private static final String SUBJECT_ID = "id_subject";
    private static final String SUBJECT_NAME = "subject_name";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TestingDaoJpaImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Stream<Subject> findall() {
        LOGGER.debug("findAll()");
        List<Subject> subject = namedParameterJdbcTemplate.query(SELECT_SQL, new SubjectRowMapper());
        return subject.stream();
    }

    @Override
    public Optional<Subject> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public int create(Subject department) {
        return 0;
    }

    @Override
    public void update(Subject department) {

    }

    @Override
    public void delete(Integer id) {
    }

    private class SubjectRowMapper implements RowMapper<Subject> {
        @Override
        public Subject mapRow(ResultSet resultSet, int i) throws SQLException {
            Subject subject = new Subject();
            subject.setIdSubject(resultSet.getInt(SUBJECT_ID));
            subject.setSubjectName(resultSet.getString(SUBJECT_NAME));
            return subject;
        }
    }
}
