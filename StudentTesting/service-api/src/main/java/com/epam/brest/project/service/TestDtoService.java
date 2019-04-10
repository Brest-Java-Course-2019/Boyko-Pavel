package com.epam.brest.project.service;

import com.epam.brest.project.DTO.TestDto;

/**
 * Service interface for TestDto.
 */
public interface TestDtoService {
    /**
     * Gets TestDto by id.
     *
     * @param id testDto id.
     * @return TestDto.
     */
    TestDto findTestDtoById(Integer id);

    /**
     * Add TestDto.
     *
     * @param testDto TestDto to add.
     */
    void addTestDto(TestDto testDto);

    /**
     * Delete TestDto by id.
     *
     * @param id TestDto for delete.
     */
    void deleteTestDto(Integer id);

    /**
     * Update TestDto.
     *
     * @param testDto TestDto for update.
     */
    void updateTestDto(TestDto testDto);

}
