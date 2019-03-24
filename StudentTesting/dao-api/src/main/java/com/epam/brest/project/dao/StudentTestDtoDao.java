package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.StudentTestDto;

import java.util.stream.Stream;

public interface StudentTestDtoDao {

    Stream<StudentTestDto> findAllDto();

}
