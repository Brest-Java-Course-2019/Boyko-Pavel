package com.epam.brest.project.web_app.validators;


import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.model.Question;
import com.epam.brest.project.model.QuestionItem;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;


@Component
public class TestValidator implements Validator {

    private static final String LIST_TO_ADD = "add";
    private static final String LIST_TO_UPDATE = "update";

    @Override
    public boolean supports(Class<?> clazz) {
        return TestDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "testName", "testName.empty");

        TestDto testDto = (TestDto) target;
        if (StringUtils.hasLength(testDto.getTestName().trim())
                && testDto.getTestName().length() > 256) {
            errors.rejectValue("testName", "testName.maxSize255");
        }

        validateQuestion(testDto.getQuestions(), errors, LIST_TO_UPDATE);
        validateQuestionItem(testDto.getQuestionItems(), errors, LIST_TO_UPDATE);
        validateQuestion(testDto.getQuestionsToAdd(), errors, LIST_TO_ADD);
        validateQuestionItem(testDto.getQuestionItemsToAdd(), errors, LIST_TO_ADD);
    }

    private void validateQuestion(List<Question> questionList, Errors errors, String version) {
        for (int i = 0; i < questionList.size(); i++) {
            if (questionList.get(i).getQuestionName().trim().length() == 0) {
                if (version.equals(LIST_TO_UPDATE)) {
                    errors.rejectValue("questions[" + i + "].questionName", "testQuestion.empty");
                } else {
                    errors.rejectValue("questionsToAdd[" + i + "].questionName", "testQuestion.empty");
                }
            }
        }
    }

    private void validateQuestionItem(List<List<QuestionItem>> list, Errors errors, String version) {
        for (int i = 0; i < list.size(); i++) {
            List<QuestionItem> questionItem = list.get(i);
            int countUncheckedAnswers = 0;
            for (int j = 0; j < questionItem.size(); j++) {
                if (questionItem.get(j).getDescription().trim().length() == 0) {
                    if (version.equals(LIST_TO_UPDATE)) {
                        errors.rejectValue("questionItems[" + i + "][" + j +
                                "]" + ".description", "testQuestionItems.empty");
                    }
                    else {
                        errors.rejectValue("questionItemsToAdd[" + i + "][" + j +
                                "]" + ".description", "testQuestionItems.empty");
                    }
                }
                if (!questionItem.get(j).getAnswer()) {
                    countUncheckedAnswers++;
                    if (countUncheckedAnswers == 4) {
                        if (version.equals(LIST_TO_UPDATE)) {
                            errors.rejectValue("questionItems[" + i + "][0].answer", "");
                        }
                        else {
                            errors.rejectValue("questionItemsToAdd[" + i + "][0].answer", "");
                        }
                    }
                }
            }
        }
    }
}
