package com.epam.brest.project.rest_app;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.model.StudentAnswer;
import com.epam.brest.project.service.StudentAnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/startTest/{id}")
public class StudentAnswerRestController implements StudentAnswerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentAnswerRestController.class);


    @Autowired
    private StudentAnswerService studentAnswerService;

    @Override
    @PostMapping
    public void addStudentAnswer(@RequestBody TestDto testDto, @PathVariable Integer id) {
        LOGGER.debug("start rest addStudentAnswer({},{})", testDto, id);
        studentAnswerService.addStudentAnswer(testDto, id);
    }


    @Override
    @GetMapping
    public List<StudentAnswer> findStudentAnswerById(@PathVariable Integer id) {
        LOGGER.debug("findStudentAnswerById({})", id);
        return studentAnswerService.findStudentAnswerById(id);
    }
}
