package com.epam.brest.project.web_app;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.service.SubjectService;
import com.epam.brest.project.service.TestDtoService;
import com.epam.brest.project.web_app.builder.TestDtoBuilder;
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

public class EditTestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditTestController.class);


    @Autowired
    private TestDtoService testDtoService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TestValidator validator;

    @GetMapping(value = {"/editTest/{id}"})
    public final String gotoEditTest(@PathVariable Integer id, Model model) {

        LOGGER.debug("gotoEditTest({}, {})", id, model);

        model.addAttribute("testDto", testDtoService.findTestDtoById(id));
        model.addAttribute("subjects", subjectService.findAll());
        return "editTest";
    }

    @PostMapping(value = {"/editTest/{id}"})
    public String updateTestById(@PathVariable Integer id, @Valid TestDto testDTO, Model model,
                                 BindingResult result) {
        LOGGER.debug("updateTestById({}, {}, {}, {})", id, testDTO, model, result);

        TestDto testDtoBuilder = new TestDtoBuilder(testDTO).getTestDto();
        validator.validate(testDtoBuilder, result);
        if (result.hasErrors()) {
            model.addAttribute("subjects", subjectService.findAll());
            return "editTest";
        } else {

            LOGGER.debug("updateTestDto({})", testDtoBuilder);

            testDTO.idTests(id);
            testDtoService.updateTestDto(testDTO);
            return "redirect:/teacher";
        }
    }

    @PostMapping(value = {"/editTest"})
    public String addNewTest(TestDto testDto, Model model,
                             BindingResult result) {

        LOGGER.debug("addNewTest({},{}, {})", testDto, model, result);

        TestDto testDtoBuilder = new TestDtoBuilder(testDto).getTestDto();
        validator.validate(testDtoBuilder, result);
        if (result.hasErrors()) {
            model.addAttribute("subjects", subjectService.findAll());
            return "editTest";
        } else {
            LOGGER.debug("updateTestDto({})", testDtoBuilder);
            testDtoService.addTestDto(testDtoBuilder);
            return "redirect:/teacher";
        }
    }

    @GetMapping(value = {"/editTest"})
    public final String createNewTest(Model model) {

        LOGGER.debug("createNewTest({})", model);

        model.addAttribute("testDto", new TestDto());
        model.addAttribute("subjects", subjectService.findAll());
        return "editTest";
    }
}
