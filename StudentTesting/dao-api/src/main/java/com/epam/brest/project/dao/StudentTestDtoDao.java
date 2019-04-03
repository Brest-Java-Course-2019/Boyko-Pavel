package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.builder.DateBuilder;

import java.text.ParseException;
import java.util.stream.Stream;

/**
 * StudentTest Dto Dao interface.
 */
public interface StudentTestDtoDao {

    /**
     * Find all StudentTestDto.
     *
     * @return stream StudentTestDto.
     */
    Stream<StudentTestDto> findAllDto();

    /**
     * Return all StudentTestDto filtering by date.
     *
     * @return stream StudentTestDto.
     */
    Stream<StudentTestDto> filterByDate(DateBuilder dateBuilder, Integer idStudent) throws ParseException;


    /**
     * Return all StudentTestDto filtering by date.
     *
     * @return stream StudentTestDto.
     */
    Stream<StudentTestDto> findNotDoneTestStudentById(Integer idStudent);
}
