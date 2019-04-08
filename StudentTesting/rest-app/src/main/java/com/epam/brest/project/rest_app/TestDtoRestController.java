package com.epam.brest.project.rest_app;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.service.TestDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/teacher")
public class TestDtoRestController implements TestDtoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestDtoRestController.class);

    @Autowired
    private TestDtoService testDtoService;

    @Override
    @GetMapping(value = "/editTest/{id}")
    public TestDto findTestDtoById(@PathVariable Integer id) {
        LOGGER.debug("start rest controller findTestDtoById({})", id);
        return testDtoService.findTestDtoById(id);
    }

    @Override
    @PostMapping(value = "/editTest")
    public void addTestDto(@RequestBody TestDto testDto) {
        LOGGER.debug("start rest controller addTestDto({})", testDto);
        testDtoService.addTestDto(testDto);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void deleteTestDto(@PathVariable Integer id) {
        LOGGER.debug("start rest controller deleteTestDto({})", id);
        testDtoService.deleteTestDto(id);
    }

    @Override
    @PutMapping(value = "/editTest")
    public void updateTestDto(@RequestBody TestDto testDto) {
        LOGGER.debug("start rest controller updateTestDto({})", testDto);
        testDtoService.updateTestDto(testDto);
    }
}
