package com.epam.brest.project.web_app.validators;


import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.model.QuestionItem;
import com.epam.brest.project.model.Teacher;
import com.epam.brest.project.service.StudentService;

import com.epam.brest.project.service.TestDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;


@Component
public class StudentAnswerValidator implements Validator {

    private int countRightAnswer;

    @Autowired
    private TestDtoService testDtoService;


    @Override
    public boolean supports(Class<?> clazz) {
        return Teacher.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        TestDto studentTestDto = (TestDto) target;

        TestDto testDtoFromDb = testDtoService.findTestDtoById(studentTestDto.getIdTests());
        int countRightAnswer = 0;
        List<List<QuestionItem>> correctAnswers = testDtoFromDb.getQuestionItems();
        for (int i = 0; i < correctAnswers.size(); i++) {
            for (int j = 0; j < correctAnswers.get(i).size(); j++) {
                String studentAnswer = studentTestDto.getQuestionItems()
                        .get(i).get(j).getAnswer().toString();
                String correctAnswer = correctAnswers.get(i).get(j).getAnswer().toString();
                if (studentAnswer.equals("true") && correctAnswer.equals("false")) {
                    errors.rejectValue("questionItems[" + i + "][" + j +
                            "]" + ".description", "answerValidator.unMarked");
                }
                if (studentAnswer.equals("false") && correctAnswer.equals("true")) {
                    errors.rejectValue("questionItems[" + i + "][" + j +
                            "]" + ".description", "answerValidator.marked");
                } else {
                    countRightAnswer++;
                }
            }
        }
        this.countRightAnswer=countRightAnswer;

    }

    public int getCountRightAnswer() {
        return this.countRightAnswer;
    }
}