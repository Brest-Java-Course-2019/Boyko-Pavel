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

/**
 * Class implements TestDtoService.
 */
@Service
@Transactional
public class TestDtoServiceImpl implements TestDtoService {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TestDtoServiceImpl.class);
    /**
     * testDao.
     */
    private TestDao testDao;
    /**
     * questionDao.
     */
    private QuestionDao questionDao;
    /**
     * questionItemDao.
     */
    private QuestionItemDao questionItemDao;
    /**
     * Create new  TestDtoServiceImpl.
     * @param testDao input value.
     * @param questionDao input value.
     * @param questionItemDao input value.
     */
    public TestDtoServiceImpl(TestDao testDao, QuestionDao questionDao, QuestionItemDao questionItemDao) {
        this.testDao = testDao;
        this.questionDao = questionDao;
        this.questionItemDao = questionItemDao;
    }

    /**
     * Method gets TestDto by id.
     * @param id testDto id.
     * @return TestDto.
     */
    @Override
    public TestDto findTestDtoById(Integer id) {

        LOGGER.debug("start findTestDtoById({})", id);

        TestDto testDto = testDao.findTestDtoById(id).get();
        testDto.setQuestions(getQuestionsList(testDto.getIdTests()));
        return testDto;
    }
    /**
     * Method get all QuestionItemList by test id for Question with id(question id) .
     * @param idTest id test.
     * @param idQuestion question id.
     * @return list QuestionItem.
     */
    private List<QuestionItem> getQuestionItemList(Integer idTest, Integer idQuestion) {

        LOGGER.debug("start getQuestion({}, {})", idTest, idQuestion);

        List<QuestionItem> questionItemList = new ArrayList<>();
        for (QuestionItem questionItem : questionItemDao.findAllQuestionItemByTestId(idTest)) {
            if (idQuestion.equals(questionItem.getQuestionId())) {
                questionItemList.add(questionItem);
            }
        }
        return questionItemList;
    }
    /**
     * Method get all Question by test id.
     * @param idTest id test.
     * @return list QuestionItem.
     */
    private List<Question> getQuestionsList(Integer idTest) {

        LOGGER.debug("start getQuestionsList({})", idTest);

        List<Question> questions = questionDao.findAllQuestionByTestId(idTest);
        for (Question question : questions) {
            question.setQuestionItems(
                    getQuestionItemList(idTest, question.getQuestionId()));
        }
        return questions;
    }

    /**
     * Method add TestDto.
     * @param testDto TestDto to add.
     */
    @Override
    public void addTestDto(TestDto testDto) {

        LOGGER.debug("addTestDto({})", testDto);

        Integer idTest = addTest(testDto);
        addQuestions(testDto.getQuestions(), idTest);
    }
    /**
     * Method add Test.
     * @param testDto TestDto store Test to add.
     * @return id added test.
     */
    private Integer addTest(TestDto testDto) {

        LOGGER.debug("start addTest({})", testDto);

        Test test = new Test();
        test.setName(testDto.getTestName());
        test.setSubjectId(testDto.getSubjectId());
        test.setTeacherId(testDto.getTeacherId());
        testDao.add(test);
        return test.getTestId();
    }
    /**
     * Method add all Question.
     * @param idTest id added test.
     * @param  questions list Question.
     */
    private void addQuestions(List<Question> questions, Integer idTest) {

        LOGGER.debug("start addQuestions({}, {})", questions, idTest);

        for (Question question : questions) {
            questionDao.add(question, idTest);
            addQuestionItems(question.getQuestionItems(), question.getQuestionId());
        }
    }
    /**
     * Method add all QuestionItem.
     * @param idQuestion id added question.
     * @param  questionItems list QuestionItem.
     */
    private void addQuestionItems(List<QuestionItem> questionItems, Integer idQuestion) {

        LOGGER.debug("start getQuestion({}, {})", questionItems, idQuestion);

        for (QuestionItem questionItem : questionItems) {
            questionItem.setQuestionId(idQuestion);
            questionItemDao.add(questionItem);
        }
    }
    /**
     * Method update TestDto.
     * @param testDto TestDto for update.
     */
    @Override
    public void updateTestDto(TestDto testDto) {

        LOGGER.debug("start updateTestDto({})", testDto);

        List<Question> questionsToUpdate = questionsToUpdate(testDto);
        Test test = new Test();
        test.setTestId(testDto.getIdTests());
        test.setName(testDto.getTestName());
        test.setSubjectId(testDto.getSubjectId());
        testDao.update(test);
        questionDao.batchUpdate(questionsToUpdate);
        questionItemDao.batchUpdate(updateQuestionItems(questionsToUpdate));
    }
    /**
     * Method update Question or if Question has id then add new Question.
     * @param testDto TestDto stores list Question.
     * @return list question for update
     */
    private List<Question> questionsToUpdate(TestDto testDto) {

        LOGGER.debug("start questionsToUpdate({})", testDto);

        List<Question> questionToUpdate = new ArrayList<>();
        List<Question> questionsToAdd = new ArrayList<>();
        for (Question question : testDto.getQuestions()) {
            if (question.getQuestionId() == null) {
                questionsToAdd.add(question);
            } else {
                questionToUpdate.add(question);
            }
        }
        addQuestions(questionsToAdd, testDto.getIdTests());
        return questionToUpdate;
    }

    /**
     * Method return list <List<QuestionItem>> to update.
     * @param questions list Question to update.
     * @return list <List<QuestionItem>> for update
     */
    private List<List<QuestionItem>> updateQuestionItems(List<Question> questions) {

        LOGGER.debug("start updateQuestionItems({})", questions);

        List<List<QuestionItem>> questionItems = new ArrayList<>();
        for (Question question : questions) {
            questionItems.add(question.getQuestionItems());
        }
        return questionItems;
    }
    /**
     * Method delete TestDto by id.
     * @param id TestDto for delete.
     */
    @Override
    public void deleteTestDto(Integer id) {

        LOGGER.debug("start deleteTestDto({})", id);

        testDao.delete(id);
        questionDao.deleteByTestId(id);
        questionItemDao.deleteByTestId(id);
    }
}
