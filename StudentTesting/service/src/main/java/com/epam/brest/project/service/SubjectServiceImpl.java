package com.epam.brest.project.service;

import com.epam.brest.project.dao.SubjectDao;
import com.epam.brest.project.model.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class implements SubjectService.
 */
@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectServiceImpl.class);
    /**
     * subjectDao.
     */
    private SubjectDao subjectDao;
    /**
     * Create new  SubjectServiceImpl.
     * @param subjectDao input value.
     */
    public SubjectServiceImpl(SubjectDao subjectDao) {

        this.subjectDao = subjectDao;
    }
    /**
     * Method gets all Subject.
     *
     * @return list Subject.
     */
    @Override
    public List<Subject> findAll() {

        LOGGER.debug("start findAllSubject()");

        return subjectDao.findAll().collect(Collectors.toList());
    }
}
