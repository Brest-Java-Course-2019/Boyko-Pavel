package com.epam.brest.project.dao;

import com.epam.brest.project.model.Question;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Question Dao Interface.
 */
public interface QuestionDao {

    /**
     * Get all question.
     *
     * @return question stream.
     */
    Stream<Question> findAll();


    /**
     * Get all question.
     *
     * @param id test id.
     * @return question list.
     */
    List<Question> findAllQuestionByTestId(Integer id);

    /**
     * Get question.
     *
     * @param id question id.
     * @return question.
     */
    Optional<Question> findById(final Integer id);

    /**
     * Add question.
     *
     * @param question object question.
     * @param idTest   test id.
     * @return question.
     */
    Optional<Question> add(final Question question, Integer idTest);

    /**
     * Update question.
     *
     * @param question object question.
     */
    void update(final Question question);

    /**
     * Delete question.
     *
     * @param id test id.
     */
    void deleteByTestId(final int id);

    /**
     * Butch update question.
     *
     * @param question question list.
     */
    void batchUpdate(final List<Question> question);
}
