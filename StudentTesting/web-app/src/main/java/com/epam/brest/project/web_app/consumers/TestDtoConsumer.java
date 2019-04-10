package com.epam.brest.project.web_app.consumers;


import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.service.TestDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Class TestDtoConsumer implements TeacherService
 */
public class TestDtoConsumer implements TestDtoService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TestDtoConsumer.class);

    /**
     * Request url
     */
    private String url;

    /**
     * Rest template
     */
    private RestTemplate restTemplate;

    /**
     * TestDtoConsumer constructor.
     *
     * @param url          request url
     * @param restTemplate rest Template
     */
    public TestDtoConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    /**
     * Method get testDto through rest service.
     *
     * @param id testDto id
     * @return body of response entity TestDto
     */
    @Override
    public TestDto findTestDtoById(Integer id) {
        ResponseEntity responseEntity = restTemplate
                .getForEntity(url + "/editTest/" + id, TestDto.class);
        return (TestDto) responseEntity.getBody();
    }

    /**
     * Method add testDto through rest service.
     *
     * @param testDto testDto to add
     */
    @Override
    public void addTestDto(TestDto testDto) {
        LOGGER.debug("addTestDto({})", testDto);
        restTemplate.postForEntity(url + "/editTest", testDto, TestDto.class);
    }

    /**
     * Method delete testDto through rest service.
     *
     * @param id testDto to delete
     */
    @Override
    public void deleteTestDto(Integer id) {
        LOGGER.debug("deleteTestDto({})", id);
        restTemplate.delete(url + "/" + id);
    }

    /**
     * Method update testDto through rest service.
     *
     * @param testDto testDto to update
     */
    @Override
    public void updateTestDto(TestDto testDto) {
        restTemplate.put(url + "/editTest", testDto, TestDto.class);
    }
}


