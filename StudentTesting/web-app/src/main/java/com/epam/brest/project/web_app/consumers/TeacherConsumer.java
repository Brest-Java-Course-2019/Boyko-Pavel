package com.epam.brest.project.web_app.consumers;


import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.model.Teacher;
import com.epam.brest.project.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Class TeacherConsumer implements TeacherService
 */
public class TeacherConsumer implements TeacherService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherConsumer.class);


    /**
     * Request url
     */
    private String url;

    /**
     * Rest template
     */
    private RestTemplate restTemplate;

    /**
     * TeacherConsumer constructor.
     *
     * @param url          request url
     * @param restTemplate rest Template
     */
    public TeacherConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    /**
     * Method get teacher through rest service.
     *
     * @return body of response entity teacher
     */
    @Override
    public Teacher findTeacherByLogin(Teacher teacher) {
        LOGGER.debug("consumer findAll()");
        ResponseEntity responseEntity = restTemplate.postForEntity(url, teacher, Teacher.class);
        return (Teacher) responseEntity.getBody();
    }

    /**
     * Method get student testDto through rest service.
     *
     * @return body of response entity list student testDto
     */
    @Override
    public List<StudentTestDto> findAllDtoTestTeacher(Integer id) {
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/" + id, List.class);
        return (List<StudentTestDto>) responseEntity.getBody();
    }
}
