package com.epam.brest.project.rest_app;

import com.epam.brest.project.model.Subject;
import com.epam.brest.project.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest Controller for Subject.
 */
@RestController
@RequestMapping(value = "/subject")
public class SubjectRestController implements SubjectService {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectRestController.class);
    /**
     * Service.
     */
    @Autowired
    private SubjectService subjectService;

    /**
     * Method gets all Subject.
     *
     * @return list Subject.
     */
    @Override
    @GetMapping
    public List<Subject> findAll() {
        LOGGER.debug("start rest controller findAll()");
        return subjectService.findAll();
    }
}
