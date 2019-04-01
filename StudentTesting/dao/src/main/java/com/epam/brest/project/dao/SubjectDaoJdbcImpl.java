package com.epam.brest.project.dao;


import com.epam.brest.project.model.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.beans.factory.annotation.Value;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class SubjectDaoJdbcImpl implements SubjectDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectDaoJdbcImpl.class);

    private static final String CHECK_COUNT_NAME = "SELECT count(subject_id) FROM subject WHERE lower(subject_name) = lower(:subject_name)";

    private static final String SUBJECT_ID = "subject_id";
    private static final String SUBJECT_NAME = "subject_name";

    @Value("${subject.selectAllSubject}")
    private String selectAllSubject;

    @Value("${subject.selectBySubjectId}")
    private String selectBySubjectId;

    @Value("${subject.insertSubject}")
    private String insertSubject;

    @Value("${subject.updateSubject}")
    private String updateSubject;

    @Value("${subject.deleteSubject}")
    private String deleteSubject;


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SubjectDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Find all Subject.
     *
     * @return Subject stream.
     */
    @Override
    public Stream<Subject> findall() {
        LOGGER.debug("findAll()");
        List<Subject> subject = namedParameterJdbcTemplate.query(selectAllSubject, new SubjectRowMapper());
        return subject.stream();
    }

    /**
     * Find Subject by id.
     *
     * @param id subject.
     * @return subject.
     */
    @Override
    public Optional<Subject> findById(Integer id) {
        LOGGER.debug("findBuId({})", id);
        SqlParameterSource namedParameter = new MapSqlParameterSource(SUBJECT_ID, id);
        Subject subject = namedParameterJdbcTemplate.queryForObject(selectBySubjectId, namedParameter, new SubjectRowMapper());
        return Optional.ofNullable(subject);
    }

    /**
     * Add new Subject.
     *
     * @param subject new Subject.
     */
    @Override
    public Optional<Subject> add(Subject subject) {
        LOGGER.debug("add({})", subject);
        return Optional.of(subject)
                .filter(this::isNameUnique)
                .map(this::insertSubject)
                .orElseThrow(() -> new IllegalArgumentException("Subject with the this name already exist in DB.subject"));
    }

    /**
     * Check exist subject name in db.
     *
     * @param subject Subject.
     */
    private boolean isNameUnique(Subject subject) {
        Integer getCountIdSubject = namedParameterJdbcTemplate.queryForObject(CHECK_COUNT_NAME,
                new MapSqlParameterSource(SUBJECT_NAME, subject.getName()),
                Integer.class);
        return getCountIdSubject == 0;
    }

    /**
     * Add new Subject.
     *
     * @param subject new Subject.
     */
    private Optional<Subject> insertSubject(Subject subject) {
        LOGGER.info("add({})", subject);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(SUBJECT_NAME.toLowerCase(), subject.getName().toLowerCase());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertSubject, mapSqlParameterSource, generatedKeyHolder);
        subject.setSubjectId(generatedKeyHolder.getKey().intValue());

        return Optional.of(subject);
    }

    /**
     * Update Subject.
     *
     * @param subject Subject for updating.
     */
    @Override
    public void update(Subject subject) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(SUBJECT_NAME, subject.getName());
        mapSqlParameterSource.addValue(SUBJECT_ID, subject.getSubjectId());
        Optional.of(namedParameterJdbcTemplate.update(updateSubject, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new NegativeArraySizeException("Failed to update subject in DB"));
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    /**
     * Delete subject.
     *
     * @param idSubject id Subject for deleting.
     */
    @Override
    public void delete(int idSubject) {
        Optional.of(namedParameterJdbcTemplate.update(deleteSubject, new MapSqlParameterSource(SUBJECT_ID, idSubject)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete subject from DB"));
    }

    private class SubjectRowMapper implements RowMapper<Subject> {
        /**
         * @param resultSet the RowMapper which creates an object for each row
         * @param i         the number of expected rows
         * @return new question
         */
        @Override
        public Subject mapRow(ResultSet resultSet, int i) throws SQLException {
            Subject subject = new Subject();
            subject.setSubjectId(resultSet.getInt(SUBJECT_ID));
            subject.setName(resultSet.getString(SUBJECT_NAME));
            return subject;
        }
    }
}
