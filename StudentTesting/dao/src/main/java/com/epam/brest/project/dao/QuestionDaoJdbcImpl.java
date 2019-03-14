package com.epam.brest.project.dao;

import com.epam.brest.project.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;


public class QuestionDaoJdbcImpl implements QuestionDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionDaoJdbcImpl.class);

    private static final String QUESTION_ID = "question_id";
    private static final String QUESTION = "question";
    private static final String TEST_ID = "test_id";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${question.selectAllQuestion}")
    private String selectAllQuestionItem;

    @Value("${question.selectQuestionById}")
    private String selectQuestionById;

    @Value("${question.insertQuestion}")
    private String insertQuestionItem;

    @Value("${question.existTestById}")
    private String existTestById;

    @Value("${question.updateQuestion}")
    private String updateQuestionItem;

    @Value("${question.deleteQuestion}")
    private String deleteQuestionItem;

    public QuestionDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Stream<Question> findall() {
        LOGGER.warn("start findall()");
        List<Question> questionItems = namedParameterJdbcTemplate.query(selectAllQuestionItem, new QuestionRowMapper());
        return questionItems.stream();
    }

    @Override
    public Optional<Question> findById(Integer id) {
        LOGGER.warn("start findById()");
        Question question = namedParameterJdbcTemplate.queryForObject(selectQuestionById,
                new MapSqlParameterSource(QUESTION_ID, id), new QuestionRowMapper());
        return Optional.ofNullable(question);
    }

    @Override
    public Optional<Question> add(Question question) {
        LOGGER.warn("start add()");
        return Optional.of(question)
                .filter(this::existTest)
                .map(this::insertQuestionItem)
                .orElseThrow(() -> new IllegalArgumentException("Enter exist question"));
    }

    private Boolean existTest(Question question) {
        return namedParameterJdbcTemplate.queryForObject(existTestById,
                new MapSqlParameterSource(TEST_ID, question.getTestId()),
                Integer.class) != 0;
    }

    private Optional<Question> insertQuestionItem(Question question) {
        LOGGER.warn("start insertQuestionItem()");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(QUESTION, question.getQuestion());
        mapSqlParameterSource.addValue(TEST_ID, question.getTestId());

        KeyHolder generatorKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertQuestionItem, mapSqlParameterSource, generatorKeyHolder);
        Map<String, Object> keyMap = generatorKeyHolder.getKeys();
        question.setQuestionId((Integer) keyMap.get(QUESTION_ID));
        return Optional.of(question);
    }

    @Override
    public void update(Question question) {
        LOGGER.warn("start update()");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(QUESTION, question.getQuestion());
        mapSqlParameterSource.addValue(QUESTION_ID, question.getQuestionId());
        Optional.of(namedParameterJdbcTemplate.update(updateQuestionItem, mapSqlParameterSource))
                .filter(this::countAffectedRow)
                .orElseThrow(() -> new IllegalArgumentException("Failed to update question"));
    }

    private Boolean countAffectedRow(int numRowsUpdated){
        return numRowsUpdated > 0;
    }
    @Override
    public void delete(int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(QUESTION_ID, id);
        Optional.of(namedParameterJdbcTemplate.update(deleteQuestionItem, mapSqlParameterSource))
                .filter(this::countAffectedRow)
                .orElseThrow(() -> new RuntimeException("Failed to delete question"));
    }

    private class QuestionRowMapper implements RowMapper<Question> {
        @Override
        public Question mapRow(ResultSet resultSet, int i) throws SQLException {
            Question question = new Question();
            question.setQuestionId(resultSet.getInt(QUESTION_ID));
            question.setQuestion(resultSet.getString(QUESTION));
            question.setTestId(resultSet.getInt(TEST_ID));
            return question;
        }
    }
}
