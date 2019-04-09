package com.epam.brest.project.web_app.consumers;


import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.model.StudentAnswer;
import com.epam.brest.project.service.StudentAnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Class StudentAnswerConsumer implements StudentAnswerService
 */
public class StudentAnswerConsumer implements StudentAnswerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentAnswerConsumer.class);

    /**
     * Request url.
     */
    private String url;

    /**
     * Rest template.
     */
    private RestTemplate restTemplate;

    /**
     * StudentAnswerConsumer constructor.
     *
     * @param url          request url.
     * @param restTemplate rest Template.
     */
    public StudentAnswerConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    /**
     * method addStudentAnswer add add student answer through rest service.
     *
     * @param testDto   object which stores the answers.
     * @param studentId id student.
     */
    @Override
    public void addStudentAnswer(TestDto testDto, Integer studentId) {
        LOGGER.debug("addStudentAnswer({}, {})", testDto, studentId);
        restTemplate.postForEntity(url + "/"+ studentId, testDto, TestDto.class);
    }

    /**
     * method findAll() gets list student answer through rest service.
     *
     * @return body of response entity student answer.
     */
    @Override
    public List<StudentAnswer> findStudentAnswerById(Integer studentId) {
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/"+ studentId, List.class);
        return (List<StudentAnswer>) responseEntity.getBody();
    }
}
