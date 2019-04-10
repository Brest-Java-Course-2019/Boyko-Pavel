package com.epam.brest.project.service;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.model.Teacher;

import java.util.List;

/**
 * Service interface for Teacher.
 */
public interface TeacherService {
    /**
     * Gets Teacher by login.
     *
     * @param teacher object stored login
     * @return Teacher.
     */
    Teacher findTeacherByLogin(Teacher teacher);

    /**
     * Gets StudentTestDto by teacher id.
     *
     * @param id student id
     * @return list StudentTestDto.
     */
    List<StudentTestDto> findAllDtoTestTeacher(Integer id);
}
