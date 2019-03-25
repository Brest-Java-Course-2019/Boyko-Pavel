package com.epam.brest.project.web_app;

import com.epam.brest.project.model.Teacher;
import com.epam.brest.project.service.StudentService;
import com.epam.brest.project.service.TestDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



@Controller
@SessionAttributes({ "teacher"})
@ContextConfiguration(locations = {"classpath*:test-db.xml"})
public class TeacherController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherController.class);


//    @ModelAttribute("teacher")
//    public Teacher todos() {
//        return new Teacher();
//    }

    @Autowired()
    private StudentService studentService;

    @Autowired
    private TestDtoService testDtoService;

    @PostMapping(value = {"/start"})
    public String updateTestById(Model model, Teacher teacher,
                                 BindingResult result) {

        LOGGER.debug("findAllTeacherTest({}, {})", teacher, result);
//        teacherValidator.validate(teacher, result);
        String view = "";
        Teacher teacherDb = studentService.findTeacherByLogin(teacher.getLogin());
        if (teacher.getLogin().equals(teacherDb.getLogin())) {
            if (teacher.getPassword().equals(teacherDb.getPassword())){
                model.addAttribute("teacher", teacherDb);
//                model.addAttribute("login", teacherDb.getLogin());
//                model.addAttribute("login", teacherDb.getLogin());
//                model.addAttribute("id", teacherDb.getTeacherId());
//                HttpSession session = request.getSession(true);
//                session.setAttribute("login", teacherDb.getLogin());
//                session.setAttribute("password", teacherDb.getPassword());
//                session.setAttribute("id", teacherDb.getTeacherId());
                view = "redirect:/teacher" ;
            }
            else {
                view = "redirect:/start";
            }
        } else {
            view = "redirect:/start";
        }
        return view;
    }

    @GetMapping(value = {"/teacher"})
    public final String createNewTest(@ModelAttribute Teacher teacher, Model model) {

        LOGGER.debug("findAllSubject({})", model);
//        Teacher teacher = studentService.findTeacherByLogin();
//        Teacher teacher = studentService.findTeacherByLogin("admin1");
        studentService.findAllDtoTestTeacher(teacher.getTeacherId());
        model.addAttribute("teacherTestsDto", studentService.findAllDtoTestTeacher(teacher.getTeacherId()));
        return "teacher";
    }


    @GetMapping(value = {"/teacher/{id}/delete"})
    public final String deleteTestById(@PathVariable Integer id, Model model) {
        LOGGER.debug("deleteTestById({},{})", id, model);
        testDtoService.deleteTestDto(id);
        return "redirect:/teacher";
    }
}

