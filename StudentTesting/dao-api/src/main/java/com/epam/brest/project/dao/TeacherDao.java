package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.model.Teacher;

import java.util.Optional;
import java.util.stream.Stream;

public interface TeacherDao {

    Stream<StudentTestDto> findAllDtoTeacher(Integer id);

    Optional<Teacher> findTeacherByLogin(String login);
}
