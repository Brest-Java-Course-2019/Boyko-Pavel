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
        List<List<QuestionItem>> questionItemsList = new ArrayList<>();
        for (Question question : testDTO.getQuestions()) {
            questionItemsList.add(getQuestionItemForIdQuestion(
                    questionItemDao.findAllQuestionItemByTestId(id), question.getQuestionId()));
        }
        testDTO.setQuestionItems(questionItemsList);
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
        Integer testId = testDto.getIdTests();
        if (testDto.getIdTests() == null){
            test.setSubjectId(testDto.getSubjectId());
            test.setName(testDto.getTestName());
            test.setTeacherId(testDto.getTeacherId());
            testDao.add(test);
            testId = test.getTestId();
        }
        List<Integer> questionId = addQuestion(testDto.getQuestionsToAdd(), testId);
        for (int x = 0; x < questionId.size(); x++) {
            addQuestionItem(testDto.getQuestionItemsToAdd().get(x), questionId.get(x));
        }
    }

    private List<Integer> addQuestion(List<Question> questionList, Integer testId) {
        List<Integer> arrayQuestionId = new ArrayList<>();
        for (Question question : questionList) {
            Question questionToAdd = new Question();
            questionToAdd.setQuestionName(question.getQuestionName());
            questionToAdd.setTestId(testId);
            questionDao.add(questionToAdd, testId);
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
    public void deleteTestDto(Integer id) {
        testDao.delete(id);
        questionDao.deleteByTestId(id);
        questionItemDao.deleteByTestId(id);
    }

    @Override
    public void updateTestDto(TestDto testDto) {
        List<Question> getQuestionsToAdd = testDto.getQuestionsToAdd();
        if (getQuestionsToAdd.size() != 0){
            addTestDto(testDto);
        }
        Test test = new Test();
        test.setTestId(testDto.getIdTests());
        test.setName(testDto.getTestName());
        test.setSubjectId(testDto.getSubjectId());
        testDao.update(test);
        questionDao.batchUpdate(testDto.getQuestions());
        questionItemDao.batchUpdate(testDto.getQuestionItems());
    }

//    private List<Question> getQuestionsToAdd(TestDto testDto) {
//        List<Question> questionToAdd = new ArrayList<>();
//        for (Question question : testDto.getQuestions()) {
//            if (question.getQuestionId() == null) {
//                questionToAdd.add(question);
//            }
//        }
//        return questionToAdd;
//    }
//
//    private List<List<QuestionItem>> getQuestionItemsToAdd(TestDto testDto){
//        List<List<QuestionItem>> questionItemToAdd = new ArrayList<>();
//        List<List<QuestionItem>> listQuestionItem = testDto.getQuestionItems();
//        List<Question> questionList = testDto.getQuestions();
//        for (int i = 0; i <questionList.size() ; i++) {
//            if (questionList.get(i).getQuestionId() == null) {
//                questionItemToAdd.add(listQuestionItem.get(i));
//            }
//        }
//        return questionItemToAdd;
//    }
}
