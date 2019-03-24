package com.epam.brest.project.service;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.model.Teacher;

import java.util.List;

public interface StudentService {

    List<StudentTestDto> findAllDto();

//    List<StudentTestDto> findAllDtoTestTeacher(String login);

    Teacher findTeacherByLogin(String login);

    List<StudentTestDto> findAllDtoTestTeacher(Integer id);

}