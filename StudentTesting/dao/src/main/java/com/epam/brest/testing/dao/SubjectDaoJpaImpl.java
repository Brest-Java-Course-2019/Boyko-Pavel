package com.epam.brest.testing.dao;

import com.epam.brest.testing.model.Subject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class SubjectDaoJpaImpl implements SubjectDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectDaoJpaImpl.class);

    private static final String SELECT_SQL = "SELECT idSubject, subjectName FROM subject";
    private static final String SELECT_SUBJECT_BY_ID = "SELECT idSubject, subjectName FROM subject WHERE idSubject = :idSubject";
    private static final String CHECK_COUN_NAME = "SELECT count(idSubject) FROM subject WHERE lower(subjectName) = lower(:subjectName)";
    private static final String INSERT = "INSERT into subject (subjectName) VALUES (:subjectName)";
    private static final String UPDATE = "UPDATE subject SET  subjectName = :subjectName";
    private static final String DELETE = "DELETE FROM subject WHERE idSubject = :idSubject";

    private static final String SUBJECT_ID = "idSubject";
    private static final String SUBJECT_NAME = "subjectName";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SubjectDaoJpaImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
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
        LOGGER.debug("findBuId({})", id);
        SqlParameterSource namedParameter = new MapSqlParameterSource(SUBJECT_ID, id);
        Subject subject = namedParameterJdbcTemplate.queryForObject(SELECT_SUBJECT_BY_ID, namedParameter, new SubjectRowMapper());
        return Optional.ofNullable(subject);
    }

    @Override
    public Optional<Subject> add(Subject subject) {
        LOGGER.debug("add({})", subject);
        return Optional.of(subject)
                .filter(this::isNameUnique)
                .map(this::insertSubject)
                .orElseThrow(() -> new IllegalArgumentException("Subject with the this name already exist in DB.subject"));
    }

    private boolean isNameUnique(Subject subject) {
        Integer getCounIdSubject = namedParameterJdbcTemplate.queryForObject(CHECK_COUN_NAME,
                new MapSqlParameterSource(SUBJECT_NAME, subject.getSubjectName()),
                Integer.class);
        return getCounIdSubject == 0;
    }

    private Optional<Subject> insertSubject(Subject subject) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(SUBJECT_NAME, subject.getSubjectName());
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(INSERT, mapSqlParameterSource, generatedKeyHolder);
//        LOGGER.info("add( result update = {}, keyholder = {})", getKeySubject, generatedKeyHolder.getKey().intValue());
        subject.setIdSubject(generatedKeyHolder.getKey().intValue());
        return Optional.of(subject);
    }

    @Override
    public void update(Subject department) {
        Optional.of(namedParameterJdbcTemplate.update(UPDATE, new MapSqlParameterSource(SUBJECT_NAME, department.getSubjectName())))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new NegativeArraySizeException("Failed to update subject in DB"));
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    @Override
    public void delete(int idSubject) {
        Optional.of(namedParameterJdbcTemplate.update(DELETE, new MapSqlParameterSource(SUBJECT_ID, idSubject)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete subject from DB"));
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
