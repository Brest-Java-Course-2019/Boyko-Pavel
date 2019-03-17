package com.epam.brest.project.service;

import com.epam.brest.project.dao.SubjectDao;
import com.epam.brest.project.model.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectServiceImpl.class);

    private SubjectDao subjectDao;

    public SubjectServiceImpl(SubjectDao subjectDao) {

        this.subjectDao = subjectDao;
    }

    @Override
    public List<Subject> findAllSubject() {
        LOGGER.debug("start findAllSubject()");
        return subjectDao.findall().collect(Collectors.toList());
    }
}
