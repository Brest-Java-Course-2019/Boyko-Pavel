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

/**
 * Rest Controller for Student.
 */
@RestController
@RequestMapping(value = "/student")
public class StudentRestController implements StudentService {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentRestController.class);
    /**
     * Service.
     */
    @Autowired
    private StudentService studentService;

    /**
     * Method gets all StudentTestDto.
     *
     * @return list StudentTestDto.
     */
    @Override
    @GetMapping
    public List<StudentTestDto> findAllDto() {
        LOGGER.debug("start rest controller findAllDto()");
        return this.studentService.findAllDto();
    }

    /**
     * Method filtered by date StudentTestDto.
     *
     * @param id          student id
     * @param dateBuilder object stored startDate and endDate
     * @return list StudentTestDto.
     */
    @Override
    @PostMapping(value = "/filter/{id}")
    public List<StudentTestDto> filterByDate(@RequestBody DateBuilder dateBuilder,
                                             @PathVariable Integer id) throws ParseException {
        LOGGER.debug("start rest controller filterByDate({}, {})", dateBuilder, id);
        Integer studentId = id;
        if (id == 0) {
            studentId = null;
        }
        return studentService.filterByDate(dateBuilder, studentId);
    }

    /**
     * Method get Student by login.
     *
     * @param student object stored student login
     * @return Student.
     */
    @Override
    @PostMapping
    public Student findStudentByLogin(@RequestBody Student student) {
        LOGGER.debug("start rest controller findStudentByLogin({})", student);
        return studentService.findStudentByLogin(student);
    }

    /**
     * Method gets all StudentTestDto by id student, id can be null then return all StudentTestDto.
     *
     * @param id student id
     * @return list StudentTestDto.
     */
    @Override
    @GetMapping(value = "/{id}")
    public List<StudentTestDto> findAllDtoTestStudent(@PathVariable Integer id) {
        LOGGER.debug("start rest controller findAllDtoTestStudent({})", id);
        return studentService.findAllDtoTestStudent(id);
    }
}
