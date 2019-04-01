package com.epam.brest.project.service;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.builder.DateBuilder;

import java.text.ParseException;
import java.util.List;

public interface StudentService {

    List<StudentTestDto> findAllDto();

    List<StudentTestDto> filterByDate(DateBuilder dateBuilder) throws ParseException;
}