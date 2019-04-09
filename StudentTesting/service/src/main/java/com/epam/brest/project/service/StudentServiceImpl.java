package com.epam.brest.project.service;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.builder.DateBuilder;
import com.epam.brest.project.dao.StudentDao;
import com.epam.brest.project.dao.StudentTestDtoDao;
import com.epam.brest.project.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    private StudentTestDtoDao studentTestDtoDao;

    private StudentDao studentDao;


    public StudentServiceImpl(StudentTestDtoDao studentTestDtoDao, StudentDao studentDao) {
        this.studentTestDtoDao = studentTestDtoDao;
        this.studentDao = studentDao;
    }

    @Override
    public List<StudentTestDto> findAllDto() {

        LOGGER.debug("start findAllDto()");

        return studentTestDtoDao.findAllDto().collect(Collectors.toList());
    }

    @Override
    public List<StudentTestDto> filterByDate(DateBuilder dateBuilder, Integer studentId) throws ParseException {

        LOGGER.debug("filterByDate({})", dateBuilder);

        return studentTestDtoDao.filterByDate(dateBuilder, studentId).collect(Collectors.toList());
    }


    @Override
    public Student findStudentByLogin(Student student) {
        Student studentRespond;
        try{
            studentRespond = studentDao.findByLogin(student.getLogin()).get();
        }
        catch (EmptyResultDataAccessException e){
            studentRespond = null;
        }
        return studentRespond;
    }

    @Override
    public List<StudentTestDto> findAllDtoTestStudent(Integer id) {
        return studentTestDtoDao.findNotDoneTestStudentById(id).collect(Collectors.toList());
    }


}
