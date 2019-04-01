package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.model.Teacher;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * TeacherDao Interface.
 */
public interface TeacherDao {

    /**
     * Find all StudentTestDto by teacher id.
     *
     * @param teacherId id teacher
     * @return StudentTestDto stream.
     */
    Stream<StudentTestDto> findAllDtoTeacher(Integer teacherId);

    /**
     * Find all teacher by teacher login.
     *
     * @param login login teacher.
     * @return teacher by login.
     */
    Optional<Teacher> findTeacherByLogin(String login);
}
