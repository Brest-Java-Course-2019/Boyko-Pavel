package com.epam.brest.project.service;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.dao.QuestionDao;
import com.epam.brest.project.dao.QuestionItemDao;
import com.epam.brest.project.dao.TestDao;
import com.epam.brest.project.model.Question;
import com.epam.brest.project.model.QuestionItem;
import com.epam.brest.project.model.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TestDtoServiceImpl implements TestDtoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestDtoServiceImpl.class);

    private TestDao testDao;
    private QuestionDao questionDao;
    private QuestionItemDao questionItemDao;

    public TestDtoServiceImpl(TestDao testDao, QuestionDao questionDao, QuestionItemDao questionItemDao) {
        this.testDao = testDao;
        this.questionDao = questionDao;
        this.questionItemDao = questionItemDao;
    }


    @Override
    public TestDto findTestDtoById(Integer id) {
        LOGGER.debug("Find findTestDtoById()");
        TestDto testDto = testDao.findTestDtoById(id).get();
        testDto.setQuestions(getQuestionsList(testDto.getIdTests()));
        return testDto;
    }

    private List<QuestionItem> getQuestion(Integer idTest, Integer idQuestion) {
        List<QuestionItem> questionItemList = new ArrayList<>();
        for (QuestionItem questionItem : questionItemDao.findAllQuestionItemByTestId(idTest)) {
            if (idQuestion.equals(questionItem.getQuestionId())) {
                questionItemList.add(questionItem);
            }
        }
        return questionItemList;
    }

    private List<Question> getQuestionsList(Integer idTest) {
        List<Question> questions = questionDao.findAllQuestionByTestId(idTest);
        for (Question question : questions) {
            question.setQuestionItems(
                    getQuestion(idTest, question.getQuestionId()));
        }
        return questions;
    }


    @Override
    public void addTestDto(TestDto testDto) {
        Integer idTest = addTest(testDto);
        addQuestions(testDto.getQuestions(), idTest);
    }

    private Integer addTest(TestDto testDto) {
        Test test = new Test();
        test.setName(testDto.getTestName());
        test.setSubjectId(testDto.getSubjectId());
        test.setTeacherId(testDto.getTeacherId());
        testDao.add(test);
        return test.getTestId();
    }

    private void addQuestions(List<Question> questions, Integer idTest) {
        for (Question question : questions) {
            questionDao.add(question, idTest);
            addQuestionItems(question.getQuestionItems(), question.getQuestionId());
        }
    }

    private void addQuestionItems(List<QuestionItem> questionItems, Integer idQuestion) {
        for (QuestionItem questionItem : questionItems) {
            questionItem.setQuestionId(idQuestion);
            questionItemDao.add(questionItem);
        }
    }

    @Override
    public void updateTestDto(TestDto testDto) {
        List<Question> questionsToUpdate = questionsToUpdate(testDto);
        Test test = new Test();
        test.setTestId(testDto.getIdTests());
        test.setName(testDto.getTestName());
        test.setSubjectId(testDto.getSubjectId());
        testDao.update(test);
        questionDao.batchUpdate(questionsToUpdate);
        questionItemDao.batchUpdate(updateQuestionItems(questionsToUpdate));
    }

    private List<Question> questionsToUpdate(TestDto testDto) {
        List<Question> questionToUpdate = new ArrayList<>();
        for (Question question : testDto.getQuestions()) {
            if (question.getQuestionId() == null) {
                questionDao.add(question, testDto.getIdTests());
            } else {
                questionToUpdate.add(question);
            }
        }
        return questionToUpdate;
    }


    private List<List<QuestionItem>> updateQuestionItems(List<Question> questions) {
        List<List<QuestionItem>> questionItems = new ArrayList<>();
        for (Question question : questions) {
            questionItems.add(question.getQuestionItems());
        }
        return questionItems;
    }

    @Override
    public void deleteTestDto(Integer id) {
        testDao.delete(id);
        questionDao.deleteByTestId(id);
        questionItemDao.deleteByTestId(id);
    }
}
