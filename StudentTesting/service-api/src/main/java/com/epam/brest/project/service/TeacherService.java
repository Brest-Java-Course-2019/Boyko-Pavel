package com.epam.brest.project.service;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.model.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher findTeacherByLogin(Teacher teacher);

    List<StudentTestDto> findAllDtoTestTeacher(Integer id);
}
