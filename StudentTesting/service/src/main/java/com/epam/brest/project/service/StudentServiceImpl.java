package com.epam.brest.project.service;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.dao.StudentTestDtoDao;
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

    private StudentTestDtoDao studentTestDtoDao;


    public StudentServiceImpl(StudentTestDtoDao studentTestDtoDao) {
        this.studentTestDtoDao = studentTestDtoDao;
    }

    @Override
    public List<StudentTestDto> findAllDto() {
        LOGGER.debug("Find all student test DTO");
        return studentTestDtoDao.findAllDto().collect(Collectors.toList());
    }
}
