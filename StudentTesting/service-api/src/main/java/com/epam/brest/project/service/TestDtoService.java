package com.epam.brest.project.service;

import com.epam.brest.project.DTO.TestDto;

public interface TestDtoService {

    TestDto findTestDtoById(Integer id);

    void addTestDto(TestDto testDto);

    void deleteTestDto(Integer id);

    void updateTestDto(TestDto testDto);

}
