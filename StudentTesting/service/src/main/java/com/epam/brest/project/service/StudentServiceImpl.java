package com.epam.brest.project.service;

import com.epam.brest.project.DTO.StudentTestDTO;
import com.epam.brest.project.dao.StudentTestDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements  StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    private StudentTestDao studentTestDao;

    public StudentServiceImpl(StudentTestDao studentTestDao) {
        this.studentTestDao = studentTestDao;
    }

    @Override
    public List<StudentTestDTO> findAllDto() {
        LOGGER.debug("Find all student test DTO");
        return studentTestDao.findallDto().collect(Collectors.toList());
    }
}
