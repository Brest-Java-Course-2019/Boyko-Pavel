package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.builder.DateBuilder;

import java.text.ParseException;
import java.util.stream.Stream;

public interface StudentTestDtoDao {

    Stream<StudentTestDto> findAllDto();

    Stream<StudentTestDto> filterByDate(DateBuilder dateBuilder) throws ParseException;
}
