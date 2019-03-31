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
        List<Question> newQuestions;
        if (testDto.getQuestions() == null) {
             newQuestions = new ArrayList<>();
        }
        else{
             newQuestions = testDto.getQuestions();
        }
        List<List<QuestionItem>> newQuestionItems = buildQuestionItem();
        for (int i = 0; i <questions.length ; i++) {
            Question question = new Question();
            question.setQuestionName(questions[i]);
            question.setQuestionItems(newQuestionItems.get(i));
            newQuestions.add(question);
        }
        testDto.setQuestions(newQuestions);
    }

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
        return listsQuestionItem;
    }

    public TestDto getTestDto() {
        if (testDto.getNewQuestion() != null) {
            buildQuestion();
            testDto.setNewQuestion(null);
            testDto.setNewAnswer(null);
            testDto.setNewDescription(null);
            LOGGER.debug("getTestDto({})", testDto);
        }
        return testDto;
    }
}
