package com.epam.brest.project.service;

import com.epam.brest.project.DTO.StudentTestDto;



import java.util.List;

public interface StudentService {

    List<StudentTestDto> findAllDto();
}