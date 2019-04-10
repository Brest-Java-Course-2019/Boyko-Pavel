package com.epam.brest.project.rest_app;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.model.StudentAnswer;
import com.epam.brest.project.service.StudentAnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Controller for StudentAnswer.
 */
@RestController
@RequestMapping(value = "/startTest/{id}")
public class StudentAnswerRestController implements StudentAnswerService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentAnswerRestController.class);

    /**
     * Service.
     */
    @Autowired
    private StudentAnswerService studentAnswerService;

    /**
     * Method add StudentAnswer.
     *
     * @param testDto stored student answer.
     * @param id      student id
     */
    @Override
    @PostMapping
    public void addStudentAnswer(@RequestBody TestDto testDto, @PathVariable Integer id) {
        LOGGER.debug("start rest addStudentAnswer({},{})", testDto, id);
        studentAnswerService.addStudentAnswer(testDto, id);
    }

    /**
     * Method get all StudentAnswer by student id.
     *
     * @param id student id
     * @return List<StudentAnswer>.
     */
    @Override
    @GetMapping
    public List<StudentAnswer> findStudentAnswerById(@PathVariable Integer id) {
        LOGGER.debug("findStudentAnswerById({})", id);
        return studentAnswerService.findStudentAnswerById(id);
    }
}
