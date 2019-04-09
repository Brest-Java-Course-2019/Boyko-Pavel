package com.epam.brest.project.web_app.consumers;


import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.builder.DateBuilder;
import com.epam.brest.project.model.Student;
import com.epam.brest.project.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Class StudentConsumer implements StudentService
 */
public class StudentConsumer implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentConsumer.class);

    /**
     * Request url
     */
    private String url;

    /**
     * Rest template
     */
    private RestTemplate restTemplate;

    /**
     * StudentConsumer constructor.
     *
     * @param url          request url
     * @param restTemplate rest Template
     */
    public StudentConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    /**
     * method findAllDto() filtered by date list student testDto through rest service.
     *
     * @return body of response entity list student testDto
     */
    @Override
    public List<StudentTestDto> findAllDto() {
        LOGGER.debug("consumer findAllDto()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<StudentTestDto>) responseEntity.getBody();
    }

    /**
     * method filterByDate() gets list student testDto through rest service.
     *
     * @param dateBuilder object which store date
     * @param studentId   student id
     * @return body of response entity list student testDto
     */
    @Override
    public List<StudentTestDto> filterByDate(DateBuilder dateBuilder, @PathVariable Integer studentId) {
        LOGGER.debug("consumer filterByDate({}, {})", dateBuilder, studentId);
        Integer id = studentId;
        if (studentId == null) {
            id = 0;
        }
        ResponseEntity responseEntity = restTemplate.postForEntity(url + "/filter/" + id,
                dateBuilder, List.class);
        return (List<StudentTestDto>) responseEntity.getBody();
    }


    /**
     * method findStudentByLogin() gets student through rest service.
     *
     * @param student object stored login
     * @return body of response entity student
     */
    @Override
    public Student findStudentByLogin(@RequestBody Student student) {
        LOGGER.debug("consumer findStudentByLogin({})", student);
        ResponseEntity responseEntity = restTemplate.postForEntity(url, student, Student.class);
        return (Student) responseEntity.getBody();
    }

    /**
     * method findAllDtoTestStudent() gets student testDto through rest service.
     *
     * @param id student id
     * @return body of response entity student
     */
    @Override
    public List<StudentTestDto> findAllDtoTestStudent(Integer id) {
        LOGGER.debug("consumer findAllDtoTestStudent({})", id);
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/" + id, List.class);
        return (List<StudentTestDto>) responseEntity.getBody();
    }
}
