package com.epam.brest.project.web_app;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.service.SubjectService;
import com.epam.brest.project.service.TestDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@Controller
@ContextConfiguration(locations = {"classpath*:test-db.xml"})

public class EditTestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditTestController.class);


    @Autowired
    private TestDtoService testDtoService;

    @Autowired
    private SubjectService subjectService;

//    @Autowired
//    TestValidator validator;
    @GetMapping(value = {"/editTest/{id}"})
    public final String gotoEditTest(@PathVariable Integer id, Model model) {

        LOGGER.debug("findTestDtoById({}, {})", id, model);
        model.addAttribute("testDto", testDtoService.findTestDtoById(id));
        model.addAttribute("subjects", subjectService.findAll());
        return "editTest";
    }

    @PostMapping(value = {"/editTest/{id}"})
    public String updateTestById(@Valid TestDto testDTO,
                                        BindingResult result) {
        LOGGER.debug("updateTestById({}, {})", testDTO, result);
        if (result.hasErrors()){
            return "editTest";
        }
        else {
            TestDto testToAdd = new TestDto();
            testToAdd.setTeacherId(testDTO.getTeacherId());
            testToAdd.setNewAnswer(testDTO.getNewAnswer());
            testToAdd.setNewDescription(testDTO.getNewDescription());
            testToAdd.setNewQuestion(testDTO.getNewQuestion());
            LOGGER.debug("addTestDto({},{})", testDTO, result);
            testDtoService.updateTestDto(testDTO);
            return "redirect:/teacher";
        }
    }

    @PostMapping(value = {"/editTest"})
    public String addNewTest(TestDto testDto,
                                BindingResult result) {
        LOGGER.debug("addTestDto({},{})", testDto, result);
        testDtoService.addTestDto(testDto);
        return "redirect:/teacher";
    }

    @GetMapping(value = {"/editTest"})
    public final String createNewTest(Model model) {
        LOGGER.debug("findAllSubject({})", model);
        model.addAttribute("testDto", new TestDto());
        model.addAttribute("subjects", subjectService.findAll());
        return "editTest";
    }

}
