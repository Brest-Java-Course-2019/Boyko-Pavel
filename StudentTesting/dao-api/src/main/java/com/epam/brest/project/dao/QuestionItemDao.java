package com.epam.brest.project.dao;

import com.epam.brest.project.model.QuestionItem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface QuestionItemDao {

    Stream<QuestionItem> findall();

    List<QuestionItem> findallQuestionItemByQuestionId(Integer id);

    Optional<QuestionItem> findById(final Integer id);

    Optional<QuestionItem> add(final QuestionItem questionItem);

    void update(final QuestionItem questionItem);

    void delete(final int id);

    void batchUpdate(List<List<QuestionItem>> questionItems);

    void batchDelete(List<List<QuestionItem>> questionItems);
}
