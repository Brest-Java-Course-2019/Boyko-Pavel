package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.model.Teacher;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * TeacherDao interface.
 */
public interface TeacherDao {

    /**
     * Find all StudentTestDto by Teacher id.
     *
     * @param teacherId id Teacher
     * @return StudentTestDto stream.
     */
    Stream<StudentTestDto> findAllDtoTeacher(Integer teacherId);

    /**
     * Find all teacher by Teacher login.
     *
     * @param login login Teacher.
     * @return Teacher by login.
     */
    Optional<Teacher> findTeacherByLogin(String login);
}
