//package com.epam.brest.project.web_app.validators;
//
//
//import com.epam.brest.project.model.Teacher;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//import org.springframework.validation.Validator;
//
//@Component
//public class TeacherValidator implements Validator {
//
//    private static final int DEPARTMENT_NAME_MAX_SIZE = 255;
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return Teacher.class.equals(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//
//        ValidationUtils.rejectIfEmpty(errors, "departmentName", "departmentName.empty");
//        Teacher department = (Teacher) target;
//        Teacher teacher =
//        if (StringUtils.hasLength(target.g())
//                && department.getDepartmentName().length() > DEPARTMENT_NAME_MAX_SIZE) {
//            errors.rejectValue("departmentName", "departmentName.maxSize255");
//        }
//    }
//}
