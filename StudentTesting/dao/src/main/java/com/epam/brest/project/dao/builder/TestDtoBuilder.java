package com.epam.brest.project.dao.builder;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.model.Question;
import com.epam.brest.project.model.QuestionItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * TestDtoBuilder class.
 */
public class TestDtoBuilder {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TestDtoBuilder.class);
    /**
     * TestDto.
     */
    private final TestDto testDto;

    /**
     * Constructor gets TestDto.
     *
     * @param testDto input value.
     */
    public TestDtoBuilder(TestDto testDto) {
        this.testDto = testDto;
    }

    /**
     * Method set TestDto Question from newQuestions.
     */
    private void buildQuestion() {
        String[] questions = testDto.getNewQuestion();
        List<Question> newQuestions;
        if (testDto.getQuestions() == null) {
            newQuestions = new ArrayList<>();
        } else {
            newQuestions = testDto.getQuestions();
        }
        List<List<QuestionItem>> newQuestionItems = buildQuestionItem();
        for (int i = 0; i < questions.length; i++) {
            Question question = new Question();
            question.setQuestionName(questions[i]);
            question.setQuestionItems(newQuestionItems.get(i));
            newQuestions.add(question);
        }
        testDto.setQuestions(newQuestions);

        LOGGER.debug("end buildQuestion({})", testDto);
    }

    /**
     * Method set TestDto QuestionItem from newDescription and newAnswer.
     * @return list <List<QuestionItem>>
     */
    private List<List<QuestionItem>> buildQuestionItem() {
        List<List<QuestionItem>> listsQuestionItem = new ArrayList<>();
        String[] newDescription = testDto.getNewDescription();
        Boolean[] newAnswer = testDto.getNewAnswer();
        for (int i = 0; i < newAnswer.length; i += 4) {
            List<QuestionItem> questionItemList = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                QuestionItem questionItem = new QuestionItem();
                questionItem.setDescription(newDescription[i + j]);
                questionItem.setAnswer(newAnswer[i + j]);
                questionItemList.add(questionItem);
            }
            listsQuestionItem.add(questionItemList);

        }

        LOGGER.debug("end buildQuestionItem({})", testDto);

        return listsQuestionItem;
    }

    /**
     * Method get TestDto from form and return TesDto.
     * @return TestDto
     */
    public TestDto getTestDto() {
        if (testDto.getNewQuestion() != null) {
            buildQuestion();
            testDto.setNewQuestion(null);
            testDto.setNewAnswer(null);
            testDto.setNewDescription(null);
            LOGGER.debug("end getTestDto({})", testDto);
        }
        return testDto;
    }

    /**
     * Method set TestDto answer null.
     *
     * @param testDto input testDto
     * @return testDto
     */
    public static TestDto setAnswerFalse(TestDto testDto) {
        for (Question question : testDto.getQuestions()) {
            for (QuestionItem questionItem : question.getQuestionItems()) {
                questionItem.setAnswer(false);
            }
        }

        LOGGER.debug("end setAnswerFalse({})", testDto);

        return testDto;
    }
}
