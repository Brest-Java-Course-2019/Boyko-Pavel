package com.epam.brest.project.dao;

import com.epam.brest.project.model.Question;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * QuestionDao interface.
 */
public interface QuestionDao {

    /**
     * Get all Question.
     *
     * @return Question stream.
     */
    Stream<Question> findAll();


    /**
     * Get all Question.
     *
     * @param id Test id.
     * @return Question list.
     */
    List<Question> findAllQuestionByTestId(Integer id);

    /**
     * Get Question.
     *
     * @param id Question id.
     * @return Question.
     */
    Optional<Question> findById(final Integer id);

    /**
     * Add question.
     *
     * @param question Question to add.
     * @param idTest   test id.
     * @return Question.
     */
    Optional<Question> add(final Question question, Integer idTest);

    /**
     * Update Question.
     *
     * @param question Question to update.
     */
    void update(final Question question);

    /**
     * Delete Question.
     *
     * @param id Test id.
     */
    void deleteByTestId(final int id);

    /**
     * Batch update Question.
     *
     * @param question Question list to update.
     */
    void batchUpdate(final List<Question> question);
}
