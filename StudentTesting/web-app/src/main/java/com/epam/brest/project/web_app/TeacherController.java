package com.epam.brest.project.web_app;

import com.epam.brest.project.model.Student;
import com.epam.brest.project.model.Teacher;
import com.epam.brest.project.service.StudentService;
import com.epam.brest.project.service.TeacherService;
import com.epam.brest.project.service.TestDtoService;
import com.epam.brest.project.web_app.validators.TeacherValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@SessionAttributes({"teacher"})
@ContextConfiguration(locations = {"classpath*:test-db.xml"})
public class TeacherController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherController.class);


    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TestDtoService testDtoService;

    @Autowired
    private TeacherValidator teacherValidator;

    @PostMapping(value = {"/teacher"})
    public String getTestDtoAfterLogin(Model model, Teacher teacher,
                                 BindingResult result) {

        LOGGER.debug("getTestDtoAfterLogin({}, {}, {})", model, teacher, result);

        teacherValidator.validate(teacher, result);
        if (result.hasErrors()){

            LOGGER.debug("after un correct validation getTestDtoAfterLogin({})", result);

            model.addAttribute("student", new Student());
            model.addAttribute("studentTestsDTOs", studentService.findAllDto());
            return "start";
        }
        else{

            LOGGER.debug("after un correct validation getTestDtoAfterLogin({})", result);

            model.addAttribute("teacher", teacherValidator.getTeacherValidation());
            return "redirect:/teacher";
        }
    }


    @GetMapping(value = {"/teacher"})
    public final String createNewTest(@ModelAttribute Teacher teacher, Model model) {

        LOGGER.debug("createNewTest({})", model);

        teacherService.findAllDtoTestTeacher(teacher.getTeacherId());
        model.addAttribute("teacherTestsDto", teacherService.findAllDtoTestTeacher(teacher.getTeacherId()));
        return "teacher";
    }


    @GetMapping(value = {"/teacher/{id}/delete"})
    public final String deleteTestById(@PathVariable Integer id, Model model) {

        LOGGER.debug("deleteTestById({},{})", id, model);

        testDtoService.deleteTestDto(id);
        return "redirect:/teacher";
    }
}

