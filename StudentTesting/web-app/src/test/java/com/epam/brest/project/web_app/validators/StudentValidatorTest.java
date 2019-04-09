//package com.epam.brest.project.web_app.validators;
//
//import com.epam.brest.project.DTO.TestDto;
//import com.epam.brest.project.builder.DateBuilder;
//import com.epam.brest.project.model.Student;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.BeanPropertyBindingResult;
//import org.springframework.validation.BindingResult;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//class StudentValidatorTest {
//
//    private Student student;
//
//    @Autowired
//    private StudentValidator studentValidator;
//
//    private BindingResult result;
//
//    @BeforeEach
//    void setup() {
//        student = Mockito.mock(Student.class);
//        result = new BeanPropertyBindingResult(student, "Student");
//    }
//
//    @Test
//    void shouldRunSupportsMethod() {
//
//        studentValidator.supports(DateBuilder.class);
//
//        assertFalse(result.hasErrors());
//    }
//
//    @Test
//    void shouldRejectEmptyQuestion(){
//        Mockito.when(student.getLogin()).thenReturn(null);
//
//        studentValidator.validate(student, result);
//
//        assertTrue(result.hasErrors());
//    }
//
//}