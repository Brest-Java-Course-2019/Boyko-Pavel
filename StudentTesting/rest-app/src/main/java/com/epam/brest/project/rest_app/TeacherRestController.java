package com.epam.brest.project.rest_app;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.model.Teacher;
import com.epam.brest.project.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/teacher")
public class TeacherRestController implements TeacherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherRestController.class);

    @Autowired
    private TeacherService teacherService;

    @Override
    @PostMapping
    public Teacher findTeacherByLogin(@RequestBody String login) {
        LOGGER.debug("start rest controller findTeacherByLogin({})", login);
        return teacherService.findTeacherByLogin(login);
    }

    @Override
    @GetMapping(value = "/{id}")
    public List<StudentTestDto> findAllDtoTestTeacher(@PathVariable Integer id) {
        LOGGER.debug("start rest controller findAllDtoTestTeacher({})", id);
        return teacherService.findAllDtoTestTeacher(id);
    }
}
