package com.epam.brest.project.web_app.validators;


import com.epam.brest.project.model.Teacher;
import com.epam.brest.project.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class TeacherValidator implements Validator {

    private Teacher teacherValidation;

    @Autowired
    private TeacherService teacherService;


    @Override
    public boolean supports(Class<?> clazz) {
        return Teacher.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Teacher teacherFromForm = (Teacher) target;
        try {
            Teacher teacher = teacherService.findTeacherByLogin(teacherFromForm.getLogin());
            if (!teacher.getPassword().equals(teacherFromForm.getPassword())) {

                errors.rejectValue("password", "password.unCorrect");
            }else {
                this.teacherValidation = teacher;
            }

        } catch (EmptyResultDataAccessException ex) {
            errors.rejectValue("login", "login.unCorrect");
        }
    }

    public Teacher getTeacherValidation() {
        return teacherValidation;
    }
}

