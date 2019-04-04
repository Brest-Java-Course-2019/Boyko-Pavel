package com.epam.brest.project.service;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.model.StudentAnswer;

import java.util.List;

public interface StudentAnswerService {


    void addStudentAnswer(TestDto testDto, Integer studentId);

    List<StudentAnswer> findStudentAnswerById(Integer studentId);
}