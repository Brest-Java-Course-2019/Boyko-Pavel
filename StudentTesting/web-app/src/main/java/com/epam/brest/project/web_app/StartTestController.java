package com.epam.brest.project.web_app;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.builder.DateBuilder;
import com.epam.brest.project.model.Student;
import com.epam.brest.project.service.StudentService;
import com.epam.brest.project.service.TestDtoService;
import com.epam.brest.project.web_app.builder.TestDtoBuilder;
import com.epam.brest.project.web_app.validators.FilterValidator;
import com.epam.brest.project.web_app.validators.StudentAnswerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;


@Controller
@SessionAttributes({"student"})
@ContextConfiguration(locations = {"classpath*:test-db.xml"})

public class StartTestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartTestController.class);


    @Autowired
    private TestDtoService testDtoService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentAnswerValidator answerValidator;

    @Autowired
    private FilterValidator filterValidator;

    @GetMapping(value = {"/startTest/training"})

    public final String goToTrainingTest(Model model) {

        LOGGER.debug("goToTrainingTest({})", model);

        model.addAttribute("dateBuilder", new DateBuilder());
        model.addAttribute("allTestsDto", studentService.findAllDto());
        return "student";
    }

    @GetMapping(value = {"/startTest/training/{id}"})

    public final String goToSolveTrainingTest(@PathVariable Integer id, Model model) {

        LOGGER.debug("goToSolveTrainingTest({}, {})", id, model);

        TestDto testDto = testDtoService.findTestDtoById(id);
        model.addAttribute("testDto", TestDtoBuilder.setAnswerFalse(testDto));
        return "startTest";
    }

    @PostMapping(value = {"/startTest/training/{id}"})
    public String endSolveTrainingTest(@PathVariable Integer id, @Valid TestDto testDto,
                                       Model model, BindingResult result) {

        LOGGER.debug("endSolveTrainingTest({}, {}, {}, {})", id, testDto, model, result);

        testDto.setIdTests(id);
        answerValidator.validate(testDto, result);
        model.addAttribute("countRightQuestion", answerValidator.getCountRightAnswer());
        model.addAttribute("endTest", true);
        return "startTest";
    }


    @PostMapping(value = {"/test/sort"})
    public String sortTrainingTest(@ModelAttribute Student student, @Valid DateBuilder dateBuilder, Model model,
                                   BindingResult result) throws ParseException {

        LOGGER.debug("endSolveTrainingTest({}, {}, {})", dateBuilder, model, result);

        filterValidator.validate(dateBuilder, result);
        if (result.hasErrors()) {

            LOGGER.debug("after un correct validation endSolveTrainingTest({})", result);

            return "student";

        } else {

            LOGGER.debug("sortTrainingTest after correct validation({})", dateBuilder);

            model.addAttribute("allTestsDto", studentService.filterByDate(dateBuilder, student.getStudentId()));
            return "student";
        }
    }
}
