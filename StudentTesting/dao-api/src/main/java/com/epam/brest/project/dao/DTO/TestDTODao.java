package com.epam.brest.project.dao.DTO;

import com.epam.brest.project.DTO.StudentTestDTO;

import java.util.stream.Stream;

public interface TestDTODao {

    Stream<TestDTODao> findall();

    Stream<StudentTestDTO> findallDto();
}
