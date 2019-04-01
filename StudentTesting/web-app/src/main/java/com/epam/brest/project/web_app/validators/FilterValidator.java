package com.epam.brest.project.web_app.validators;


import com.epam.brest.project.DTO.TestDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class FilterValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return TestDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "startDate", "startDate.empty");
        ValidationUtils.rejectIfEmpty(errors, "endDate", "endDate.empty");
    }
}
