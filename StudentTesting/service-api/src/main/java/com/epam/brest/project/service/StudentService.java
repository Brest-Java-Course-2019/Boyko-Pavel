package com.epam.brest.project.service;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.builder.DateBuilder;
import com.epam.brest.project.model.Student;

import java.text.ParseException;
import java.util.List;
/**
 * Service interface for Student.
 */
public interface StudentService {
    /**
     * All StudentTestDto.
     *
     * @return list StudentTestDto.
     */
    List<StudentTestDto> findAllDto();
    /**
     * Filtered by date StudentTestDto.
     *
     * @param studentId          student id
     * @param dateBuilder object stored startDate and endDate
     * @return list StudentTestDto.
     */
    List<StudentTestDto> filterByDate(DateBuilder dateBuilder, Integer studentId) throws ParseException;
    /**
     * Student by login.
     *
     * @param student object stored student login
     * @return Student.
     */
    Student findStudentByLogin(Student student);
    /**
     * All StudentTestDto by id student.
     *
     * @param id student id
     * @return list StudentTestDto.
     */
    List<StudentTestDto> findAllDtoTestStudent(Integer id);
}