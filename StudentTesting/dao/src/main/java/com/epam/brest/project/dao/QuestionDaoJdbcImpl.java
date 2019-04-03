package com.epam.brest.project.dao;

import com.epam.brest.project.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Stream;


public class QuestionDaoJdbcImpl implements QuestionDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionDaoJdbcImpl.class);

    private static final String QUESTION_ID = "question_id";
    private static final String QUESTION_NAME = "question";
    private static final String TEST_ID = "test_id";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${question.selectAllQuestion}")
    private String selectAllQuestion;

    @Value("${question.selectQuestionById}")
    private String selectQuestionById;

    @Value("${question.insertQuestion}")
    private String insertQuestion;

    @Value("${question.selectAllQuestionByTestId}")
    private String selectAllQuestionByTestId;

    @Value("${question.updateQuestion}")
    private String updateQuestion;

    @Value("${question.deleteQuestionByTestId}")
    private String deleteQuestions;

    public QuestionDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Get all question.
     *
     * @return question stream.
     */
    @Override
    public Stream<Question> findAll() {
        LOGGER.warn("start findall()");
        List<Question> questionItems = namedParameterJdbcTemplate.query(
                selectAllQuestion, new QuestionRowMapper());
        return questionItems.stream();
    }

    /**
     * Get all question.
     *
     * @param id test id.
     * @return question list.
     */
    @Override
    public List<Question> findAllQuestionByTestId(Integer id) {
        LOGGER.warn("start findallQuestionByTestId()");
        Map<String, Integer> map = new HashMap<>();
        map.put(TEST_ID, id);
        new HashMap<>().put(TEST_ID, id);
        return namedParameterJdbcTemplate.query(selectAllQuestionByTestId,
                map, new QuestionRowMapper());
    }

    /**
     * Get question.
     *
     * @param id question id.
     * @return question.
     */
    @Override
    public Optional<Question> findById(Integer id) {
        LOGGER.warn("start findById()");
        Question question = namedParameterJdbcTemplate.queryForObject(
                selectQuestionById,
                new MapSqlParameterSource(QUESTION_ID, id),
                new QuestionRowMapper());
        return Optional.ofNullable(question);
    }

    /**
     * Add question.
     *
     * @param question object question.
     * @param idTest   test id.
     * @return question.
     */
    @Override
    public Optional<Question> add(Question question, Integer idTest) {
        LOGGER.warn("start add()");
        return Optional.of(question)
                .map((Question questionNew) ->
                        insertQuestionItem(question, idTest))
                .orElseThrow(() ->
                        new IllegalArgumentException("Enter exist question"));
    }

    /**
     * Add question.
     *
     * @param question object question.
     * @param idTest   test id.
     * @return question with question id.
     */
    private Optional<Question> insertQuestionItem(Question question, Integer idTest) {
        LOGGER.warn("start insertQuestionItem()");
        MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource();
        mapSqlParameterSource.addValue(
                QUESTION_NAME, question.getQuestionName());
        mapSqlParameterSource.addValue(TEST_ID, idTest);
        KeyHolder generatorKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertQuestion,
                mapSqlParameterSource, generatorKeyHolder);
        Map<String, Object> keyMap = generatorKeyHolder.getKeys();
        question.setQuestionId((Integer) keyMap.get(QUESTION_ID));
        return Optional.of(question);
    }


    /**
     * Update question.
     *
     * @param question object question.
     */
    @Override
    public void update(Question question) {
        LOGGER.warn("start update()");
        MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource();
        mapSqlParameterSource.addValue(
                QUESTION_NAME, question.getQuestionName());
        mapSqlParameterSource.addValue(
                QUESTION_ID, question.getQuestionId());
        Optional.of(namedParameterJdbcTemplate.update(
                updateQuestion, mapSqlParameterSource))
                .filter(this::countAffectedRow)
                .orElseThrow(() ->
                    new IllegalArgumentException("Failed to update question"));
    }

    private Boolean countAffectedRow(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    /**
     * Delete question.
     *
     * @param id test id.
     */
    @Override
    public void deleteByTestId(int id) {
        MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource();
        mapSqlParameterSource.addValue(TEST_ID, id);
        Optional.of(namedParameterJdbcTemplate.update(deleteQuestions,
                mapSqlParameterSource))
                .filter(this::countAffectedRow)
                .orElseThrow(() -> new RuntimeException(
                        "Failed to delete question"));
    }

    /**
     * Butch update question.
     *
     * @param questions question list.
     */
    @Override
    public void batchUpdate(List<Question> questions) {
        SqlParameterSource[] sqlParameterSources = new SqlParameterSource[
                questions.size()];
        for (int x = 0; x < questions.size(); x++) {
            MapSqlParameterSource mapSqlParameterSource =
                    new MapSqlParameterSource();
            mapSqlParameterSource.addValue(QUESTION_NAME,
                    questions.get(x).getQuestionName());
            mapSqlParameterSource.addValue(QUESTION_ID,
                    questions.get(x).getQuestionId());
            sqlParameterSources[x] = mapSqlParameterSource;
        }
        Optional.of(namedParameterJdbcTemplate.batchUpdate(updateQuestion,
                sqlParameterSources));
    }


    private class QuestionRowMapper implements RowMapper<Question> {
        /**
         * @param resultSet the RowMapper which creates an object for each row
         * @param i         the number of expected rows
         * @return new question
         */
        @Override
        public Question mapRow(ResultSet resultSet, int i) throws SQLException {
            Question question = new Question();
            question.setQuestionId(resultSet.getInt(QUESTION_ID));
            question.setQuestionName(resultSet.getString(QUESTION_NAME));
            question.setTestId(resultSet.getInt(TEST_ID));
            return question;
        }
    }
}
