package com.epam.brest.project.web_app.validators;


import com.epam.brest.project.model.Student;
import com.epam.brest.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class StudentValidator implements Validator {

    private Student studentValidation;

    @Autowired
    private StudentService studentService;


    @Override
    public boolean supports(Class<?> clazz) {
        return Student.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Student studentFromForm = (Student) target;
        Student student = studentService.findStudentByLogin(studentFromForm);
        if (student != null) {
            if (!student.getPassword().equals(studentFromForm.getPassword())) {

                errors.rejectValue("password", "password.unCorrect");
            } else this.studentValidation = student;

        } else {
            errors.rejectValue("login", "login.unCorrect");
        }
    }

    public Student getStudentValidation() {
        return studentValidation;
    }
}

