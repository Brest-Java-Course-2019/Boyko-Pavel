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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * EditTest web controller.
 */
@Controller
@SessionAttributes({"teacher"})
public class TeacherController {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherController.class);

    /**
     * StudentService.
     */
    @Autowired
    private StudentService studentService;
    /**
     * TeacherService.
     */
    @Autowired
    private TeacherService teacherService;
    /**
     * TestDtoService.
     */
    @Autowired
    private TestDtoService testDtoService;
    /**
     * TeacherValidator.
     */
    @Autowired
    private TeacherValidator teacherValidator;

    /**
     * get teacher testDto by login.
     *
     * @param teacher object stored teacher login.
     * @param model   model add attributes used for rendering view.
     * @param result  binding result.
     * @return view redirect teacher page.
     */
    @PostMapping(value = {"/teacher"})
    public String getTestDtoAfterLogin(Model model, Teacher teacher,
                                       BindingResult result) {

        LOGGER.debug("getTestDtoAfterLogin({}, {}, {})", model, teacher, result);

        teacherValidator.validate(teacher, result);
        if (result.hasErrors()) {

            LOGGER.debug("after un correct validation getTestDtoAfterLogin({})", result);

            model.addAttribute("student", new Student());
            model.addAttribute("studentTestsDTOs", studentService.findAllDto());
            return "start";
        } else {

            LOGGER.debug("after correct validation getTestDtoAfterLogin({})", result);

            model.addAttribute("teacher", teacherValidator.getTeacherValidation());
            return "redirect:/teacher";
        }
    }

    /**
     * get teacher testDto by login.
     *
     * @param teacher session attributes.
     * @param model   model add attributes used for rendering view.
     * @return view teacher page.
     */
    @GetMapping(value = {"/teacher"})
    public final String createNewTest(@ModelAttribute Teacher teacher, Model model) {

        LOGGER.debug("createNewTest({})", model);

        teacherService.findAllDtoTestTeacher(teacher.getTeacherId());
        model.addAttribute("teacherTestsDto", teacherService.findAllDtoTestTeacher(teacher.getTeacherId()));
        return "teacher";
    }

    /**
     * delete testDto by id test.
     *
     * @param model model add attributes used for rendering view.
     * @param id    testDto id.
     * @return view redirect back to teacher page.
     */
    @GetMapping(value = {"/teacher/{id}/delete"})
    public final String deleteTestById(@PathVariable Integer id, Model model) {

        LOGGER.debug("deleteTestById({},{})", id, model);

        testDtoService.deleteTestDto(id);
        return "redirect:/teacher";
    }
}

