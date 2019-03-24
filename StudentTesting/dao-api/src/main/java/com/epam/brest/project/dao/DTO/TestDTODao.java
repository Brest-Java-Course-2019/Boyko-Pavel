package com.epam.brest.project.dao.DTO;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.DTO.TestDto;

import java.util.stream.Stream;

public interface TestDTODao {

    Stream<TestDto> findall();

    Stream<StudentTestDto> findallDto();

    void add(TestDto testDTO);
}
