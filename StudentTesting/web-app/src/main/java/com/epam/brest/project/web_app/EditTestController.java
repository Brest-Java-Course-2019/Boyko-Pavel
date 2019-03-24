package com.epam.brest.project.web_app;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.model.Question;
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

import java.util.ArrayList;


@Controller
@ContextConfiguration(locations = {"classpath*:test-db.xml"})

public class EditTestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditTestController.class);


    @Autowired
    private TestDtoService testDtoService;

    @Autowired
    private SubjectService subjectService;


    @GetMapping(value = {"/editTest/{id}"})
    public final String gotoEditTest(@PathVariable Integer id, Model model) {

        LOGGER.debug("findTestDtoById({}, {})", id, model);
        model.addAttribute("testDto", testDtoService.findTestDtoById(id));
        model.addAttribute("subjects", subjectService.findAll());
        return "editTest";
    }

    @PostMapping(value = {"/editTest/{id}"})
    public String updateTestById(TestDto testDTO,
                                        BindingResult result) {
        LOGGER.debug("addTestDto({},{})", testDTO, result);
        testDtoService.updateTestDto(testDTO);
        return "redirect:/teacher";
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

//    @PostMapping(value = {"/editTest/{id}/add"})
//    public String addNewQuestion(TestDto testDto,
//                                       BindingResult result) {
//        LOGGER.debug("-----------------------------({},{})", testDto, result);
//        testDto.addQuestion().add(new Question());
//        testDto.addQuestionItem().add(new ArrayList<>(4));
//        return "editTest";
//    }


    @RequestMapping(value="/seedstartermng", params={"add"})
    public String addRow(final TestDto testDto, final BindingResult bindingResult) {
        testDto.addQuestion().add(new Question());
        testDto.addQuestionItem().add(new ArrayList<>(4));
        return "editTest";
    }


//    /**
//     * Delete department.
//     *
//     * @return view name
//     */
//    @GetMapping(value = "/department/{id}/delete")
//    public final String deleteDepartmentById(@PathVariable Integer id, Model model) {
//        LOGGER.debug("delete({},{})", id, model);
//        testDtoService.deleteTestDto(id);
//        return "redirect:/departments";
//    }
}
