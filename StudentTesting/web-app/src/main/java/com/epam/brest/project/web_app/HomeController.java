package com.epam.brest.project.web_app;

import com.epam.brest.project.model.Student;
import com.epam.brest.project.model.Teacher;
import com.epam.brest.project.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"student"})
@ContextConfiguration(locations = {"classpath*:test-db.xml"})
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);


    @Autowired()
    private StudentService studentService;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("student", new Student());
    }

    @GetMapping(value = {"/"})
    public final String redirectStudentTests() {

        LOGGER.debug("redirect");

        return "redirect:/start";
    }

    @GetMapping(value = {"/start"})
    public final String studentTests(Model model) {

        LOGGER.debug("studentTests({})", model);

        model.addAttribute("student", new Student());
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("studentTestsDTOs", studentService.findAllDto());
        return "start";
    }
}
