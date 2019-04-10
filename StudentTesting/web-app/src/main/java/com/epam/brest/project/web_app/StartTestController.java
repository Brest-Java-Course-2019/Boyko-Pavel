package com.epam.brest.project.web_app;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.builder.DateBuilder;
import com.epam.brest.project.dao.builder.TestDtoBuilder;
import com.epam.brest.project.model.Student;
import com.epam.brest.project.service.StudentAnswerService;
import com.epam.brest.project.service.StudentService;
import com.epam.brest.project.service.TestDtoService;
import com.epam.brest.project.web_app.validators.FilterValidator;
import com.epam.brest.project.web_app.validators.StudentAnswerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

/**
 * EditTest web controller.
 */
@Controller
@SessionAttributes({"student"})
public class StartTestController {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StartTestController.class);

    /**
     * TestDtoService.
     */
    @Autowired
    private TestDtoService testDtoService;
    /**
     * StudentService.
     */
    @Autowired
    private StudentService studentService;
    /**
     * StudentAnswerService.
     */
    @Autowired
    private StudentAnswerService answerService;
    /**
     * StudentAnswerValidator.
     */
    @Autowired
    private StudentAnswerValidator answerValidator;
    /**
     * FilterValidator.
     */
    @Autowired
    private FilterValidator filterValidator;

    /**
     * Goto student page.
     *
     * @param student session attributes
     * @param model   model add attributes used for rendering view.
     * @return view student page.
     */
    @GetMapping(value = {"/student"})
    public final String findStudentTest(@ModelAttribute Student student, Model model) {

        LOGGER.debug("createNewTest({})", model);

        model.addAttribute("dateBuilder", new DateBuilder());
        if (student.getStudentId() == null) {
            model.addAttribute("allTestsDto", studentService.findAllDto());
        } else {
            model.addAttribute("allTestsDto", studentService.findAllDtoTestStudent(student.getStudentId()));
        }
        return "student";
    }

    /**
     * Goto startTest page.
     *
     * @param id    test id
     * @param model model add attributes used for rendering view.
     * @return view startTest page.
     */
    @GetMapping(value = {"/student/startTest/{id}"})

    public final String goToSolveTrainingTest(@PathVariable Integer id, Model model) {

        LOGGER.debug("goToSolveTrainingTest({}, {})", id, model);

        TestDto testDto = testDtoService.findTestDtoById(id);
        model.addAttribute("testDto", TestDtoBuilder.setAnswerFalse(testDto));
        return "startTest";
    }

    /**
     * end solve test.
     *
     * @param student session attributes.
     * @param id      test id.
     * @param testDto TestDto object stored student answer.
     * @param model   model add attributes used for rendering view.
     * @param result  binding result.
     * @return view startTest page.
     */
    @PostMapping(value = {"/student/startTest/{id}"})
    public String endSolveTest(@ModelAttribute Student student, @PathVariable Integer id, @Valid TestDto testDto,
                               Model model, BindingResult result) {

        LOGGER.debug("endSolveTrainingTest({}, {}, {}, {})", id, testDto, model, result);

        testDto.setIdTests(id);
        if (student.getStudentId() != null) {
            answerService.addStudentAnswer(testDto, student.getStudentId());
        }
        answerValidator.validate(testDto, result);
        model.addAttribute("countRightQuestion", answerValidator.getCountRightAnswer());
        model.addAttribute("endTest", true);
        return "startTest";
    }

    /**
     * filter by date test.
     *
     * @param student     session attributes.
     * @param dateBuilder DateBuilder object stored startDate and endDate.
     * @param model       model add attributes used for rendering view.
     * @param result      binding result.
     * @return view student page.
     */
    @PostMapping(value = {"/test/sort"})
    public String filterByDateTest(@ModelAttribute Student student, @Valid DateBuilder dateBuilder, Model model,
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
