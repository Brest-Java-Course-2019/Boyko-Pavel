package com.epam.brest.project.dao;

import com.epam.brest.project.model.QuestionItem;
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


public class QuestionItemDaoJdbcImpl implements QuestionItemDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionItemDaoJdbcImpl.class);

    private static final String QUESTION_ITEM_ID = "question_item_id";
    private static final String ANSWER = "answer";
    private static final String QUESTION_ID = "question_id";
    private static final String DESCRIPTION = "description";
    private static final String TEST_ID = "test_id";
    private static final int COUNT_QUESTION_ITEM_IN_QUESTION = 4;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${questionItem.selectAllQuestionItem}")
    private String selectAllQuestionItem;

    @Value("${questionItem.selectByQuestionItem}")
    private String selectByQuestionItem;

    @Value("${questionItem.selectAllQuestionItemByTestId}")
    private String selectAllQuestionItemByTestId;

    @Value("${questionItem.insertQuestionItem}")
    private String insertQuestionItem;

    @Value("${questionItem.updateQuestionItem}")
    private String updateQuestionItem;

    @Value("${questionItem.deleteQuestionItem}")
    private String deleteQuestionItem;

    public QuestionItemDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Get all question item.
     *
     * @return QuestionItem stream.
     */
    @Override
    public Stream<QuestionItem> findall() {
        LOGGER.warn("start findall()");
        List<QuestionItem> questionItems = namedParameterJdbcTemplate.query(selectAllQuestionItem, new QuestionItemRowMapper());
        return questionItems.stream();
    }

    /**
     * Get all question item
     *
     * @param testId test id
     * @return QuestionItem stream.
     */
    @Override
    public Optional<QuestionItem> findById(Integer id) {
        LOGGER.warn("start findById()");
        QuestionItem questionItem = namedParameterJdbcTemplate.queryForObject(selectByQuestionItem,
                new MapSqlParameterSource(QUESTION_ITEM_ID, id), new QuestionItemRowMapper());
        return Optional.ofNullable(questionItem);
    }

    /**
     * Get  question item by id.
     *
     * @param id question item id.
     * @return QuestionItem bu id.
     */
    @Override
    public List<QuestionItem> findAllQuestionItemByTestId(Integer id) {
        LOGGER.warn("start findallQuestionByTestId()");
        Map<String, Integer> map = new HashMap<>();
        map.put(TEST_ID, id);
        return namedParameterJdbcTemplate.query(selectAllQuestionItemByTestId,
                map, new QuestionItemRowMapper());
    }

    /**
     * Add question item
     *
     * @param questionItem QuestionItem.
     * @return new  QuestionItem.
     */
    @Override
    public Optional<QuestionItem> add(QuestionItem questionItem) {
        LOGGER.warn("start add()");
        return Optional.of(questionItem)
                .map(this::insertQuestionItem)
                .orElseThrow(() -> new IllegalArgumentException("Enter exist question"));
    }

    /**
     * Add question item
     *
     * @param questionItem QuestionItem.
     * @return new  QuestionItem.
     */
    private Optional<QuestionItem> insertQuestionItem(QuestionItem questionItem) {
        LOGGER.warn("start insertQuestionItem()");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(ANSWER, questionItem.getAnswer());
        mapSqlParameterSource.addValue(QUESTION_ID, questionItem.getQuestionId());
        mapSqlParameterSource.addValue(DESCRIPTION, questionItem.getDescription());

        KeyHolder generatorKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertQuestionItem, mapSqlParameterSource, generatorKeyHolder);
        Map<String, Object> keyMap = generatorKeyHolder.getKeys();
        questionItem.setQuestionItemId((Integer) keyMap.get(QUESTION_ITEM_ID));
        return Optional.of(questionItem);
    }

    /**
     * Update questionItem.
     *
     * @param questionItem QuestionItem fo update.
     */
    @Override
    public void update(QuestionItem questionItem) {
        LOGGER.warn("start update()");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(ANSWER, questionItem.getAnswer());
        mapSqlParameterSource.addValue(DESCRIPTION, questionItem.getDescription());
        mapSqlParameterSource.addValue(QUESTION_ITEM_ID, questionItem.getQuestionItemId());
        Optional.of(namedParameterJdbcTemplate.update(updateQuestionItem, mapSqlParameterSource))
                .filter(this::countAffectedRow)
                .orElseThrow(() -> new IllegalArgumentException("Failed to update questionItem"));
    }


    private Boolean countAffectedRow(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    /**
     * Delete questionItem.
     *
     * @param id QuestionItem id.
     */
    @Override
    public void deleteByTestId(int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(TEST_ID, id);
        Optional.of(namedParameterJdbcTemplate.update(deleteQuestionItem, mapSqlParameterSource))
                .filter(this::countAffectedRow)
                .orElseThrow(() -> new RuntimeException("Failed to delete questionItem from DB"));
    }

    /**
     * Batch update questionItem.
     *
     * @param listList list QuestionItem list.
     */
    @Override
    public void batchUpdate(List<List<QuestionItem>> listList) {
        SqlParameterSource[] sqlParameterSources = new SqlParameterSource[
                listList.size() * COUNT_QUESTION_ITEM_IN_QUESTION];
        for (int i = 0; i < listList.size(); i++) {
            List<QuestionItem> questionItemList = listList.get(i);
            for (int j = 0; j < questionItemList.size(); j++) {
                MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
                mapSqlParameterSource.addValue(QUESTION_ITEM_ID, questionItemList.get(j).getQuestionItemId());
                mapSqlParameterSource.addValue(DESCRIPTION, questionItemList.get(j).getDescription());
                mapSqlParameterSource.addValue(ANSWER, questionItemList.get(j).getAnswer());
                sqlParameterSources[j + COUNT_QUESTION_ITEM_IN_QUESTION * i] = mapSqlParameterSource;
            }
        }
        Optional.of(namedParameterJdbcTemplate.batchUpdate(updateQuestionItem,
                sqlParameterSources));
    }


    private class QuestionItemRowMapper implements RowMapper<QuestionItem> {
        /**
         * @param resultSet the RowMapper which creates an object for each row
         * @param i         the number of expected rows
         * @return new question
         */
        @Override
        public QuestionItem mapRow(ResultSet resultSet, int i) throws SQLException {
            QuestionItem questionItem = new QuestionItem();
            questionItem.setQuestionItemId(resultSet.getInt(QUESTION_ITEM_ID));
            questionItem.setDescription(resultSet.getString(DESCRIPTION));
            questionItem.setAnswer(resultSet.getBoolean(ANSWER));
            questionItem.setQuestionId(resultSet.getInt(QUESTION_ID));
            return questionItem;
        }
    }
}
