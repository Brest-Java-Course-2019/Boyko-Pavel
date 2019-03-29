package com.epam.brest.project.service;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.model.StudentAnswer;


import java.util.List;

public interface StudentService {

    List<StudentTestDto> findAllDto();

    void addStudentAnswer(TestDto testDto, Integer studentId);

    List<StudentAnswer> findStudentAnswerById(Integer studentId);
}