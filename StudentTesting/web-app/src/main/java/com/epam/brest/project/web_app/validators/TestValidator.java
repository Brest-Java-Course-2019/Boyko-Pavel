package com.epam.brest.project.web_app.validators;


import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.model.Question;
import com.epam.brest.project.model.QuestionItem;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;


@Component
public class TestValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return TestDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "testName", "testName.empty");

        TestDto testDto = (TestDto) target;

        validateQuestion(testDto.getQuestions(), errors);
        validateQuestionItem(testDto.getQuestions(), errors);
    }

    private void validateQuestion(List<Question> questionList, Errors errors) {
        for (int i = 0; i < questionList.size(); i++) {
            if (questionList.get(i).getQuestionName().trim().length() == 0) {

                errors.rejectValue("questions[" + i + "].questionName", "testQuestion.empty");
            }
        }
    }

    private void validateQuestionItem(List<Question> list, Errors errors) {
        for (int i = 0; i < list.size(); i++) {
            List<QuestionItem> questionItem = list.get(i).getQuestionItems();
            int countUncheckedAnswers = 0;
            for (int j = 0; j < questionItem.size(); j++) {
                if (questionItem.get(j).getDescription().trim().length() == 0) {
                    errors.rejectValue("questions[" + i + "].questionItems[" + j +
                            "]" + ".description", "testQuestionItems.empty");
                }
                if (!questionItem.get(j).getAnswer()) {
                    countUncheckedAnswers++;
                    if (countUncheckedAnswers == 4) {
                        errors.rejectValue("questions[" + i + "].questionItems[0].answer", "");
                    }
                }
            }
        }
    }
}
