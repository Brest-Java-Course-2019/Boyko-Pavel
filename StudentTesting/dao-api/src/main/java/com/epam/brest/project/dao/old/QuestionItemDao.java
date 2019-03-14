package com.epam.brest.project.dao.old;

import com.epam.brest.project.model.QuestionItem;

import java.util.Optional;
import java.util.stream.Stream;

public interface QuestionItemDao {

    Stream<QuestionItem> findall();

    Optional<QuestionItem> findById(final Integer id);

    Optional<QuestionItem> add(final QuestionItem questionItem);

    void update(final QuestionItem questionItem);

    void delete(final int id);
}
