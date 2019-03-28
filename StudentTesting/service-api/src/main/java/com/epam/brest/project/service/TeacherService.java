package com.epam.brest.project.service;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.model.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher findTeacherByLogin(String login);

    List<StudentTestDto> findAllDtoTestTeacher(Integer id);
}
