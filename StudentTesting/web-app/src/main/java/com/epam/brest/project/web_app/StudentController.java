package com.epam.brest.project.web_app;

import com.epam.brest.project.builder.DateBuilder;
import com.epam.brest.project.model.Student;
import com.epam.brest.project.model.Teacher;
import com.epam.brest.project.service.StudentService;
import com.epam.brest.project.web_app.validators.StudentValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes({"student"})
@ContextConfiguration(locations = {"classpath*:test-db.xml"})
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);


    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentValidator studentValidator;

    @PostMapping(value = {"/student"})
    public String loginStudent(Model model, Student student,
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


    @GetMapping(value = {"/student"})
    public final String findStudentTest(@ModelAttribute Student student, Model model) {

        LOGGER.debug("createNewTest({})", model);

        studentService.findAllDtoTestStudent(student.getStudentId());
        model.addAttribute("dateBuilder", new DateBuilder());
        model.addAttribute("allTestsDto", studentService.findAllDtoTestStudent(student.getStudentId()));
        return "student";
    }


//    @PostMapping(value = {"/test/sort"})
//    public String sortTrainingTest(@ModelAttribute Student student, DateBuilder dateBuilder, Model model,
//                                   BindingResult result) throws ParseException {
//
//        LOGGER.debug("endSolveTrainingTest({}, {}, {})", dateBuilder, model, result);
//
//        filterValidator.validate(dateBuilder, result);
//        if (result.hasErrors()) {
//
//            LOGGER.debug("after un correct validation endSolveTrainingTest({})", result);
//
//            return "student";
//
//        } else {
//
//            LOGGER.debug("sortTrainingTest after correct validation({})", dateBuilder);
//
//            model.addAttribute("allTestsDto", studentService.filterByDate(dateBuilder, student.getStudentId()));
//            return "student";
//        }
//    }
}

