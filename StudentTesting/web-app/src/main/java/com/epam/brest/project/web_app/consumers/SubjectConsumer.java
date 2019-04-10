package com.epam.brest.project.web_app.consumers;


import com.epam.brest.project.model.Subject;
import com.epam.brest.project.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Class SubjectConsumer implements SubjectService
 */
public class SubjectConsumer implements SubjectService {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectConsumer.class);

    /**
     * Request url
     */
    private String url;

    /**
     * Rest template
     */
    private RestTemplate restTemplate;

    /**
     * SubjectConsumer constructor.
     *
     * @param url          request url
     * @param restTemplate rest Template
     */
    public SubjectConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    /**
     * Method get subject through rest service.
     *
     * @return body of response entity list subject testDto
     */
    @Override
    public List<Subject> findAll() {
        LOGGER.debug("consumer findAll()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<Subject>) responseEntity.getBody();
    }
}
