package com.epam.brest.project.dao;

import com.epam.brest.project.model.QuestionItem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * QuestionItemDao interface.
 */
public interface QuestionItemDao {

    /**
     * Get all QuestionItem.
     *
     * @return QuestionItem stream.
     */
    Stream<QuestionItem> findall();

    /**
     * Get all QuestionItem.
     *
     * @param testId test id.
     * @return QuestionItem stream.
     */
    List<QuestionItem> findAllQuestionItemByTestId(Integer testId);

    /**
     * Get QuestionItem by id.
     *
     * @param questionItemId QuestionItem id.
     * @return QuestionItem bu id.
     */
    Optional<QuestionItem> findById(final Integer questionItemId);

    /**
     * Add QuestionItem.
     *
     * @param questionItem QuestionItem to add.
     * @return new  QuestionItem.
     */
    Optional<QuestionItem> add(final QuestionItem questionItem);

    /**
     * Update QuestionItem.
     *
     * @param questionItem QuestionItem to update.
     */
    void update(final QuestionItem questionItem);

    /**
     * Delete QuestionItem.
     *
     * @param id QuestionItem id.
     */
    void deleteByTestId(final int id);

    /**
     * Batch update QuestionItem.
     *
     * @param questionItems QuestionItem list to update.
     */
    void batchUpdate(List<List<QuestionItem>> questionItems);
}
