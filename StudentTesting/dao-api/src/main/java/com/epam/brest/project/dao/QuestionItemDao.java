package com.epam.brest.project.dao;

import com.epam.brest.project.model.QuestionItem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * QuestionItem Dao Interface.
 */
public interface QuestionItemDao {

    /**
     * Get all question item.
     *
     * @return QuestionItem stream.
     */
    Stream<QuestionItem> findall();

    /**
     * Get all question item
     *
     * @param testId test id
     * @return QuestionItem stream.
     */
    List<QuestionItem> findAllQuestionItemByTestId(Integer testId);

    /**
     * Get  question item by id.
     *
     * @param questionItemId question item id.
     * @return QuestionItem bu id.
     */
    Optional<QuestionItem> findById(final Integer questionItemId);

    /**
     * Add question item
     *
     * @param questionItem QuestionItem.
     * @return new  QuestionItem.
     */
    Optional<QuestionItem> add(final QuestionItem questionItem);

    /**
     * Update questionItem.
     *
     * @param questionItem QuestionItem fo update.
     */
    void update(final QuestionItem questionItem);

    /**
     * Delete questionItem.
     *
     * @param id QuestionItem id.
     */
    void deleteByTestId(final int id);

    /**
     * Batch update questionItem.
     *
     * @param questionItems list QuestionItem list.
     */
    void batchUpdate(List<List<QuestionItem>> questionItems);
}
