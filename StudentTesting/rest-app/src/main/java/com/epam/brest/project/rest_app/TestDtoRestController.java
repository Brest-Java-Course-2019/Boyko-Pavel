package com.epam.brest.project.rest_app;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.service.TestDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller for TestDto.
 */
@RestController
@RequestMapping(value = "/teacher")
public class TestDtoRestController implements TestDtoService {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TestDtoRestController.class);
    /**
     * Service.
     */
    @Autowired
    private TestDtoService testDtoService;

    /**
     * Method gets TestDto by id.
     *
     * @param id testDto id.
     * @return TestDto.
     */
    @Override
    @GetMapping(value = "/editTest/{id}")
    public TestDto findTestDtoById(@PathVariable Integer id) {
        LOGGER.debug("start rest controller findTestDtoById({})", id);
        return testDtoService.findTestDtoById(id);
    }

    /**
     * Method add TestDto.
     *
     * @param testDto TestDto to add.
     */
    @Override
    @PostMapping(value = "/editTest")
    public void addTestDto(@RequestBody TestDto testDto) {
        LOGGER.debug("start rest controller addTestDto({})", testDto);
        testDtoService.addTestDto(testDto);
    }

    /**
     * Method delete TestDto by id.
     *
     * @param id TestDto for delete.
     */
    @Override
    @DeleteMapping(value = "/{id}")
    public void deleteTestDto(@PathVariable Integer id) {
        LOGGER.debug("start rest controller deleteTestDto({})", id);
        testDtoService.deleteTestDto(id);
    }

    /**
     * Method update TestDto.
     *
     * @param testDto TestDto for update.
     */
    @Override
    @PutMapping(value = "/editTest")
    public void updateTestDto(@RequestBody TestDto testDto) {
        LOGGER.debug("start rest controller updateTestDto({})", testDto);
        testDtoService.updateTestDto(testDto);
    }
}
