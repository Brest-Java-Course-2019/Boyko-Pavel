package com.epam.brest.project.service;

import com.epam.brest.project.DTO.TestDTO;
import com.epam.brest.project.dao.QuestionDao;
import com.epam.brest.project.dao.QuestionItemDao;
import com.epam.brest.project.dao.TestDao;
import com.epam.brest.project.model.Question;
import com.epam.brest.project.model.QuestionItem;
import com.epam.brest.project.model.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional
public class TestDTOServiceImpl implements TestDTOService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestDTOServiceImpl.class);

    private TestDao testDao;
    private QuestionDao questionDao;
    private QuestionItemDao questionItemDao;

    public TestDTOServiceImpl(TestDao testDao, QuestionDao questionDao, QuestionItemDao questionItemDao) {
        this.testDao = testDao;
        this.questionDao = questionDao;
        this.questionItemDao = questionItemDao;
    }

    @Override
    public TestDTO findTestDtoById(Integer id) {
        LOGGER.debug("Find findTestDtoById()");
        TestDTO testDTO = testDao.findTestDTOById(id).get();
        testDTO.setQuestions(questionDao.findallQuestionByTestId(id));
        List<QuestionItem> questionItems = questionItemDao.findallQuestionItemByQuestionId(id);
        List<List<QuestionItem>> questionItemsList = new ArrayList<>();
        List<QuestionItem> questionItems1 = new ArrayList<>();
        for (Question question : testDTO.getQuestions()) {
            for (QuestionItem questionItem : questionItems) {
                if (questionItem.getQuestionId().equals(question.getQuestionId())) {
                    questionItems1.add(questionItem);
                }
            }
            questionItemsList.add(questionItems1);
        }
        testDTO.setQuestionItems(questionItemsList);
        return testDTO;
    }

    @Override
    public Stream<Subject> findAllSubject() {
        return null;
    }
}
