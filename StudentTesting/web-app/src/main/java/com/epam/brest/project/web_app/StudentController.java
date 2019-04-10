package com.epam.brest.project.web_app;

import com.epam.brest.project.model.Student;
import com.epam.brest.project.model.Teacher;
import com.epam.brest.project.service.StudentService;
import com.epam.brest.project.web_app.validators.StudentValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * EditTest web controller.
 */
@Controller
@SessionAttributes({"student"})
public class StudentController {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    /**
     * studentService.
     */
    @Autowired
    private StudentService studentService;
    /**
     * studentValidator.
     */
    @Autowired
    private StudentValidator studentValidator;

    /**
     * get testDto student by login.
     *
     * @param student object stored student login.
     * @param model   model add attributes used for rendering view.
     * @param result  binding result.
     * @return view redirect back to student page.
     */
    @PostMapping(value = {"/student"})
    public String getTestDtoAfterLogin(Model model, Student student,
                                       BindingResult result) {

        LOGGER.debug("loginStudent({}, {}, {})", model, student, result);

        studentValidator.validate(student, result);
        if (result.hasErrors()) {

            LOGGER.debug("after un correct validation loginStudent({})", result);

            model.addAttribute("teacher", new Teacher());
            model.addAttribute("studentTestsDTOs", studentService.findAllDto());
            return "start";
        } else {

            LOGGER.debug("after un correct validation loginStudent({})", result);
            model.addAttribute("student", studentValidator.getStudentValidation());
            return "redirect:/student";
        }
    }
}

