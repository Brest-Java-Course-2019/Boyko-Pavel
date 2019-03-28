package com.epam.brest.project.web_app.builder;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.model.Question;
import com.epam.brest.project.model.QuestionItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class TestDtoBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestDtoBuilder.class);

    private final TestDto testDto;

    public TestDtoBuilder(TestDto testDto) {
        this.testDto = testDto;
    }

    private void buildQuestion() {
        String[] questions = testDto.getNewQuestion();
        List<Question> questionToAdd = testDto.getQuestionsToAdd();
        for (String questionName : questions) {
            Question question = new Question();
            question.setQuestionName(questionName);
            question.setTestId(testDto.getTeacherId());
            questionToAdd.add(question);
        }
        LOGGER.debug("buildQuestion()", testDto);

    }

    private void buildQuestionItem() {
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
        testDto.setQuestionItemsToAdd(listsQuestionItem);
        LOGGER.debug(" buildQuestionItem()", testDto);
    }

    public TestDto getTestDto() {
        if (testDto.getNewQuestion() != null) {
            buildQuestion();
            buildQuestionItem();
            testDto.setNewQuestion(null);
            testDto.setNewAnswer(null);
            testDto.setNewDescription(null);
            LOGGER.debug("getTestDto()", testDto);
        }
        return testDto;
    }
}
