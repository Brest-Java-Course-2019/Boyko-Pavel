package com.epam.brest.project.web_app.validators;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.builder.DateBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;

class FilterValidatorTest {

    private DateBuilder dateBuilder;

    private FilterValidator filterValidator = new FilterValidator();

    private BindingResult result;

    @BeforeEach
    void setup() {
        dateBuilder = Mockito.mock(DateBuilder.class);
        result = new BeanPropertyBindingResult(dateBuilder, "DateBuilder");
    }

    @Test
    void shouldRunSupportsMethod() {

        filterValidator.supports(DateBuilder.class);

        assertFalse(result.hasErrors());
    }

    @Test
    void shouldRejectNullStartDate() {
        Mockito.when(dateBuilder.getStartDate()).thenReturn(null);

        filterValidator.validate(dateBuilder, result);

        assertTrue(result.hasErrors());
    }


    @Test
    void shouldRejectNullEndDate() {
        Mockito.when(dateBuilder.getEndDate()).thenReturn(null);

        filterValidator.validate(dateBuilder, result);

        assertTrue(result.hasErrors());
    }
}