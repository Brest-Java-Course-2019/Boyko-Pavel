package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.model.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * TestDaoJdbcImpl implement TestDao.
 */
@Component
public class TestDaoJdbcImpl implements TestDao {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TestDaoJdbcImpl.class);
    /**
     * Constant fields.
     */
    private static final String TEST_ID = "test_id";
    private static final String TEST_NAME = "name";
    private static final String SUBJECT_NAME = "subject_name";
    private static final String TEACHER_ID = "teacher_id";
    private static final String SUBJECT_ID = "subject_id";

    /**
     * SQL select all Test.
     * type String
     */
    @Value("${test.selectAllTest}")
    private String selectAllTest;
    /**
     * SQL select Test by id.
     * type String
     */
    @Value("${test.selectTestByID}")
    private String selectTestByID;
    /**
     * SQL insert Test.
     * type String
     */
    @Value("${test.insertTest}")
    private String insertTest;
    /**
     * SQL select TestDTO by id.
     * type String
     */
    @Value("${test.selectTestDTOByID}")
    private String selectTestDTOByID;
    /**
     * SQL update Test.
     * type String
     */
    @Value("${test.updateTest}")
    private String updateTest;
    /**
     * SQL delete Test.
     * type String
     */
    @Value("${test.deleteTest}")
    private String deleteTest;

    /**
     * From property namedParameterJdbcTemplate.
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Create new  QuestionDaoJdbcImpl for the given namedParameterJdbcTemplate.
     *
     * @param namedParameterJdbcTemplate input value.
     */
    public TestDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Find all Test.
     *
     * @return Test stream.
     */
    @Override
    public Stream<Test> findAll() {

        LOGGER.warn("start findAll()");

        List<Test> tests = namedParameterJdbcTemplate.query(selectAllTest, new TestRowMapper());
        return tests.stream();
    }

    /**
     * Find all Test by id.
     *
     * @param id Test id.
     * @return Test.
     */
    @Override
    public Optional<Test> findById(Integer id) {

        LOGGER.warn("start findById({})", id);

        Test test = namedParameterJdbcTemplate.queryForObject(selectTestByID,
                new MapSqlParameterSource(TEST_ID, id), new TestRowMapper());
        return Optional.ofNullable(test);
    }

    /**
     * Find all TestDto by id.
     *
     * @param id TestDto id.
     * @return TestDto.
     */
    @Override
    public Optional<TestDto> findTestDtoById(Integer id) {

        LOGGER.debug("start findTestDtoById({})", id);

        TestDto testDTO = namedParameterJdbcTemplate.queryForObject(selectTestDTOByID,
                new MapSqlParameterSource(TEST_ID, id),
                (resultSet, i) -> new TestDto()
                        .idTests(resultSet.getInt(TEST_ID))
                        .subjectName(resultSet.getString(SUBJECT_NAME))
                        .testName(resultSet.getString(TEST_NAME))
                        .subjectId(resultSet.getInt(SUBJECT_ID))
                        .teacherId(resultSet.getInt(TEACHER_ID))
        );
        return Optional.ofNullable(testDTO);
    }

    /**
     * Add new Test.
     *
     * @param test new Test.
     * @return new Test
     */
    @Override
    public Optional<Test> add(Test test) {
        LOGGER.warn("start add()");
        return Optional.of(test)
                .map(this::insertTest)
                .orElseThrow(() -> new IllegalArgumentException("Enter exist question"));
    }

    /**
     * Add new Test.
     *
     * @param test new Test.
     * @return new Test
     */
    private Optional<Test> insertTest(Test test) {

        LOGGER.warn("start insertTest({})", test);

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(TEST_NAME, test.getName());
        mapSqlParameterSource.addValue(TEACHER_ID, test.getTeacherId());
        mapSqlParameterSource.addValue(SUBJECT_ID, test.getSubjectId());

        KeyHolder generatorKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertTest, mapSqlParameterSource, generatorKeyHolder);

        Map<String, Object> keyMap = generatorKeyHolder.getKeys();
        test.setTestId((Integer) keyMap.get(TEST_ID));
        return Optional.of(test);
    }

    /**
     * Update Test.
     *
     * @param test Test to update.
     */
    @Override
    public void update(Test test) {

        LOGGER.warn("start update({})", test);

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(TEST_ID, test.getTestId());
        mapSqlParameterSource.addValue(TEST_NAME, test.getName());
        mapSqlParameterSource.addValue(SUBJECT_ID, test.getSubjectId());
        Optional.of(namedParameterJdbcTemplate.update(updateTest, mapSqlParameterSource))
                .filter(this::countAffectedRow)
                .orElseThrow(() -> new IllegalArgumentException("Failed to update question"));
    }

    private Boolean countAffectedRow(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    /**
     * Delete Test.
     *
     * @param id Test id.
     */
    @Override
    public void delete(int id) {

        LOGGER.warn("start delete({})", id);

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(TEST_ID, id);
        Optional.of(namedParameterJdbcTemplate.update(deleteTest, mapSqlParameterSource))
                .filter(this::countAffectedRow)
                .orElseThrow(() -> new RuntimeException("Failed to delete question"));
    }

    /**
     * inner TestRowMapper implement RowMapper<Test>.
     */
    private class TestRowMapper implements RowMapper<Test> {
        /**
         * @param resultSet the RowMapper which creates an object for each row
         * @param i         the number of expected rows
         * @return new question
         */
        @Override
        public Test mapRow(ResultSet resultSet, int i) throws SQLException {
            Test test = new Test();
            test.setTestId(resultSet.getInt(TEST_ID));
            test.setName(resultSet.getString(TEST_NAME));
            test.setSubjectId(resultSet.getInt(SUBJECT_ID));
            test.setTeacherId(resultSet.getInt(TEACHER_ID));
            return test;
        }
    }
}
