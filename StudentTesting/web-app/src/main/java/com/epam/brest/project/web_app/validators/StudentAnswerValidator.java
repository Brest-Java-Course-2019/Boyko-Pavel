package com.epam.brest.project.web_app.validators;


import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.model.Question;
import com.epam.brest.project.model.QuestionItem;
import com.epam.brest.project.model.Teacher;
import com.epam.brest.project.service.TestDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;


@Component
public class StudentAnswerValidator implements Validator {

    private int countRightAnswer;

    private TestDto testDto;

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
        this.testDto = testDtoFromDb;

        int countRightAnswer = 0;
        List<Question> correctAnswers = testDtoFromDb.getQuestions();
        for (int i = 0; i < correctAnswers.size(); i++) {
            List<QuestionItem> questionItems = correctAnswers.get(i).getQuestionItems();
            int countErrorsBeforeValidation = errors.getErrorCount();
            for (int j = 0; j < questionItems.size(); j++) {
                String studentAnswer = studentTestDto.getQuestions()
                        .get(i).getQuestionItems().get(j).getAnswer().toString();

                String correctAnswer = questionItems.get(j).getAnswer().toString();
                if (studentAnswer.equals("true") && correctAnswer.equals("false")) {
                    errors.rejectValue("questions[" + i + "].questionItems[" + j +
                            "]" + ".description", "answerValidator.marked");
                }
                if (studentAnswer.equals("false") && correctAnswer.equals("true")) {

                    errors.rejectValue("questions[" + i + "].questionItems[" + j +
                            "]" + ".description", "answerValidator.unMarked");
                }
            }
            int countErrorsAfterValidation = errors.getErrorCount();
            if (countErrorsAfterValidation == countErrorsBeforeValidation) {
                countRightAnswer++;
            }
        }
        this.countRightAnswer = countRightAnswer;
    }

    public TestDto getTestDtoWithStudentAnswer(TestDto testDto) {
        List<Question> questions = this.testDto.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            List<QuestionItem> questionItems = questions.get(i).getQuestionItems();
            for (int j = 0; j < questionItems.size(); j++) {
                questionItems.get(j).setAnswer(
                        testDto.getQuestions().get(i).getQuestionItems().get(j).getAnswer());
            }
        }
        return this.testDto;
    }

    public int getCountRightAnswer() {
        return this.countRightAnswer;
    }
}