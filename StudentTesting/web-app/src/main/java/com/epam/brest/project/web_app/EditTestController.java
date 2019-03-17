package com.epam.brest.project.web_app;

import com.epam.brest.project.DTO.TestDTO;
//import com.epam.brest.project.service.SubjectService;
import com.epam.brest.project.service.TestDTOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@ContextConfiguration(locations = {"classpath*:test-db.xml"})
public class EditTestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditTestController.class);


    @Autowired
    private TestDTOService testDTOService;

//    @Autowired
//    private SubjectService subjectService;


    @GetMapping(value = {"/teacher/{id}"})
    public final String gotoEditTest(@PathVariable Integer id, Model model) {

        LOGGER.debug("findTestDtoById({}, {})",id, model);

        TestDTO testDTO = testDTOService.findTestDtoById(id);
        model.addAttribute("TestDTO", testDTO);
        return "editTest";
    }
//
//    @GetMapping(value = {"/edit"})
//    public final String createTest(Model model) {
//        LOGGER.debug("findAllSubject({})", model);
//        model.addAttribute("subjects", subjectService.findAllSubject());
//        return "edit";
//    }
}
