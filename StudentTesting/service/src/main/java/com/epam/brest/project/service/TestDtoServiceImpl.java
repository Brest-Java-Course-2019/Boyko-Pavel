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
        TestDto testDTO = testDao.findTestDTOById(id).get();
        testDTO.setQuestions(questionDao.findallQuestionByTestId(id));
        List<List<QuestionItem>> mapQuestionItemsList = new ArrayList<>();
        for (Question question : testDTO.getQuestions()) {
            mapQuestionItemsList.add(getQuestionItemForIdQuestion(
                    questionItemDao.findallQuestionItemByQuestionId(id), question.getQuestionId()));
        }
        testDTO.setQuestionItems(mapQuestionItemsList);
        return testDTO;
    }

    private List<QuestionItem> getQuestionItemForIdQuestion(List<QuestionItem> notSortedQuestionItem, Integer questionId) {
        LOGGER.debug("getQuestionItemForIdQuestion({},{}", notSortedQuestionItem, questionId);
        List<QuestionItem> questionItems = new ArrayList<>();
        for (QuestionItem questionItem : notSortedQuestionItem) {
            if (questionItem.getQuestionId().equals(questionId)) {
                questionItems.add(questionItem);
            }
        }
        return questionItems;
    }

    @Override
    public void addTestDto(TestDto testDto) {
        Test test = new Test();
        test.setSubjectId(testDto.getSubjectId());
        test.setSubjectName(testDto.getSubjectName());
        test.setName(testDto.getTestName());
        test.setTeacherId(testDto.getTeacherId());
        testDao.add(test);
        List<Integer> questionId = addQuestion(testDto.getQuestions(), test.getTestId());
        for (int x = 0; x < questionId.size(); x++) {
            addQuestionItem(testDto.getQuestionItems().get(x), questionId.get(x));
        }
    }

    private List<Integer> addQuestion(List<Question> questionList, Integer testId) {
        List<Integer> arrayQuestionId = new ArrayList<>();
        for (Question question : questionList) {
            Question questionToAdd = new Question();
            questionToAdd.setQuestionName(question.getQuestionName());
            questionToAdd.setTestId(testId);
            questionDao.add(questionToAdd);
            arrayQuestionId.add(questionToAdd.getQuestionId());
        }
        return arrayQuestionId;
    }

    private void addQuestionItem(List<QuestionItem> questionList, Integer questionId) {
        for (QuestionItem questionItem : questionList) {
            QuestionItem questionItemToAdd = new QuestionItem();
            questionItemToAdd.setAnswer(questionItem.getAnswer());
            questionItemToAdd.setDescription(questionItem.getDescription());
            questionItemToAdd.setQuestionId(questionId);
            questionItemDao.add(questionItemToAdd);
        }
    }

    @Override
    public void deleteTestDto(TestDto testDto) {
        testDao.delete(testDto.getIdTests());
        questionDao.batchDelete(testDto.getQuestions());
        questionItemDao.batchDelete(testDto.getQuestionItems());
    }

    @Override
    public void updateTestDto(TestDto testDto) {
        Test test = new Test();
        test.setTestId(testDto.getIdTests());
        test.setName(testDto.getTestName());
        test.setSubjectId(testDto.getSubjectId());
        testDao.update(test);
        questionDao.batchUpdate(testDto.getQuestions());
        questionItemDao.batchUpdate(testDto.getQuestionItems());
    }
}
