package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.builder.DateBuilder;

import java.text.ParseException;
import java.util.stream.Stream;

/**
 * StudentTestDtoDao interface.
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
     * @param dateBuilder object store startDate and endDate
     * @param idStudent   student id
     * @return stream StudentTestDto.
     * @throws ParseException if date is null
     */
    Stream<StudentTestDto> filterByDate(DateBuilder dateBuilder,
                                        Integer idStudent) throws ParseException;


    /**
     * Return all StudentTestDto filtering by date.
     *
     * @param idStudent student id
     * @return stream StudentTestDto.
     */
    Stream<StudentTestDto> findNotDoneTestStudentById(Integer idStudent);
}
