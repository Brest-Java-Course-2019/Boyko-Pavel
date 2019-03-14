package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.StudentTestDTO;

import java.util.stream.Stream;

public interface StudentTestDao {

    Stream<StudentTestDao> findall();

    Stream<StudentTestDTO> findallDto();
}
