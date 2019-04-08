package com.epam.brest.project.rest_app;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.builder.DateBuilder;
import com.epam.brest.project.model.Student;
import com.epam.brest.project.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping(value = "/student")
public class StudentRestController implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentRestController.class);

    @Autowired
    private StudentService studentService;

    @Override
    @GetMapping
    public List<StudentTestDto> findAllDto() {
        LOGGER.debug("start rest controller findAllDto()");
        return this.studentService.findAllDto();
    }

    @Override
    @PostMapping(value = "/filter/{studentId}")
    public List<StudentTestDto> filterByDate(@RequestBody DateBuilder dateBuilder,
                                             @PathVariable Integer studentId) throws ParseException {
        LOGGER.debug("start rest controller filterByDate({}, {})", dateBuilder, studentId);
        return studentService.filterByDate(dateBuilder, studentId);
    }

    @Override
    @PostMapping
    public Student findStudentByLogin(@RequestBody String login) {
        LOGGER.debug("start rest controller findStudentByLogin({})", login);
        return studentService.findStudentByLogin(login);
    }

    @Override
    @GetMapping(value = "/{id}")
    public List<StudentTestDto> findAllDtoTestStudent(@PathVariable Integer id) {
        LOGGER.debug("start rest controller findAllDtoTestStudent({})", id);
        return studentService.findAllDtoTestStudent(id);
    }
}
