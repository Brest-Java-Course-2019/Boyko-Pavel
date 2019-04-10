package com.epam.brest.project.service;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.model.StudentAnswer;

import java.util.List;

/**
 * Service interface for StudentAnswer.
 */
public interface StudentAnswerService {

    /**
     * Add StudentAnswer.
     *
     * @param testDto stored student answer.
     * @param studentId      student id
     */
    void addStudentAnswer(TestDto testDto, Integer studentId);
    /**
     * Get all StudentAnswer by student id.
     *
     * @param studentId student id
     * @return List<StudentAnswer>.
     */
    List<StudentAnswer> findStudentAnswerById(Integer studentId);
}