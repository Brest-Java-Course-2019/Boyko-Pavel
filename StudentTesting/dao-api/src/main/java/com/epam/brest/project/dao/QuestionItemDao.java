package com.epam.brest.project.dao;

import com.epam.brest.project.model.QuestionItem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface QuestionItemDao {

    Stream<QuestionItem> findall();

    List<QuestionItem> findAllQuestionItemByTestId(Integer id);

    Optional<QuestionItem> findById(final Integer id);

    Optional<QuestionItem> add(final QuestionItem questionItem);

    void update(final QuestionItem questionItem);

    void deleteByTestId(final int id);

    void batchUpdate(List<List<QuestionItem>> questionItems);
}
