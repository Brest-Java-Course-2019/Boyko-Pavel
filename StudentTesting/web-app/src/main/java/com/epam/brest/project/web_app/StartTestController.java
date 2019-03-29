package com.epam.brest.project.web_app;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.service.StudentService;
import com.epam.brest.project.service.SubjectService;
import com.epam.brest.project.service.TestDtoService;
import com.epam.brest.project.web_app.builder.TestDtoBuilder;
import com.epam.brest.project.web_app.validators.StudentAnswerValidator;
import com.epam.brest.project.web_app.validators.TestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
@ContextConfiguration(locations = {"classpath*:test-db.xml"})

public class StartTestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartTestController.class);


    @Autowired
    private TestDtoService testDtoService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentAnswerValidator answerValidator;

    @GetMapping(value = {"/startTest/training/{id}"})
    public final String goToSolveTrainingTest(@PathVariable Integer id, Model model) {
        LOGGER.debug("findTestDtoById({}, {})", id, model);
        model.addAttribute("testDto", testDtoService.findTestDtoById(id));
        return "startTest";
    }

    @PostMapping(value = {"/startTest/training/{id}"})
    public String endSolveTrainingTest(@PathVariable Integer id, @Valid TestDto testDto,
                                       Model model, BindingResult result) {
        LOGGER.debug("endSolveById({}, {})", testDto, result);
        answerValidator.validate(testDto, result);
        model.addAttribute("countRightQuestion", answerValidator.getCountRightAnswer());
        return "editTest";
    }

}
