package com.epam.brest.project.rest_app;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.model.Teacher;
import com.epam.brest.project.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Controller for Teacher.
 */
@RestController
@RequestMapping(value = "/teacher")
public class TeacherRestController implements TeacherService {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherRestController.class);
    /**
     * Service.
     */
    @Autowired
    private TeacherService teacherService;

    /**
     * Method gets Teacher by login.
     *
     * @param teacher object stored login
     * @return Teacher.
     */
    @Override
    @PostMapping
    public Teacher findTeacherByLogin(@RequestBody Teacher teacher) throws EmptyResultDataAccessException {
        LOGGER.debug("start rest controller findTeacherByLogin({})", teacher);
        return teacherService.findTeacherByLogin(teacher);
    }

    /**
     * Method gets StudentTestDto by teacher id.
     *
     * @param id student id
     * @return list StudentTestDto.
     */
    @Override
    @GetMapping(value = "/{id}")
    public List<StudentTestDto> findAllDtoTestTeacher(@PathVariable Integer id) {
        LOGGER.debug("start rest controller findAllDtoTestTeacher({})", id);
        return teacherService.findAllDtoTestTeacher(id);
    }
}
