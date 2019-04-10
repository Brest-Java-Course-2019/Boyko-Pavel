package com.epam.brest.project.web_app;

import com.epam.brest.project.model.Student;
import com.epam.brest.project.model.Teacher;
import com.epam.brest.project.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * EditTest web controller.
 */
@Controller
@SessionAttributes({"student"})
public class HomeController {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    /**
     * studentService.
     */
    @Autowired()
    private StudentService studentService;

    /**
     * set session attributes.
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("student", new Student());
    }

    /**
     * Add new TestDto.
     *
     * @return view redirect start page.
     */
    @GetMapping(value = {"/"})
    public final String redirectStudentTests() {

        LOGGER.debug("redirect");

        return "redirect:/start";
    }

    /**
     * Add new TestDto.
     *
     * @return view start page.
     */
    @GetMapping(value = {"/start"})
    public final String studentTests(Model model) {

        LOGGER.debug("studentTests({})", model);

        model.addAttribute("student", new Student());
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("studentTestsDTOs", studentService.findAllDto());
        return "start";
    }
}
