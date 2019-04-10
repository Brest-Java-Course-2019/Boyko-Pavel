package com.epam.brest.project.service;

import com.epam.brest.project.model.Subject;

import java.util.List;

/**
 * Service interface for Subject.
 */
public interface SubjectService {
    /**
     * Gets all Subject.
     *
     * @return list Subject.
     */
    List<Subject> findAll();

}
