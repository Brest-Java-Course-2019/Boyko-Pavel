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

/**
 * Class implements StudentService.
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);
    /**
     * studentTestDtoDao.
     */
    private StudentTestDtoDao studentTestDtoDao;
    /**
     * studentDao.
     */
    private StudentDao studentDao;

    /**
     * Create new  StudentServiceImpl.
     * @param studentDao input value.
     * @param studentTestDtoDao input value.
     */
    public StudentServiceImpl(StudentTestDtoDao studentTestDtoDao, StudentDao studentDao) {
        this.studentTestDtoDao = studentTestDtoDao;
        this.studentDao = studentDao;
    }

    /**
     * Method gets all StudentTestDto.
     *
     * @return list StudentTestDto.
     */
    @Override
    public List<StudentTestDto> findAllDto() {

        LOGGER.debug("start findAllDto()");

        return studentTestDtoDao.findAllDto().collect(Collectors.toList());
    }

    /**
     * Method filtered by date StudentTestDto.
     *
     * @param studentId   student id
     * @param dateBuilder object stored startDate and endDate
     * @return list StudentTestDto.
     */
    @Override
    public List<StudentTestDto> filterByDate(DateBuilder dateBuilder, Integer studentId) throws ParseException {

        LOGGER.debug("filterByDate({})", dateBuilder);

        return studentTestDtoDao.filterByDate(dateBuilder, studentId).collect(Collectors.toList());
    }

    /**
     * Method get Student by login.
     *
     * @param student object stored student login
     * @return Student.
     * @throws EmptyResultDataAccessException if login un correct
     */
    @Override
    public Student findStudentByLogin(Student student) throws EmptyResultDataAccessException {
        Student studentRespond;
        try {
            studentRespond = studentDao.findByLogin(student.getLogin()).get();
        } catch (EmptyResultDataAccessException e) {
            studentRespond = null;
        }
        return studentRespond;
    }

    /**
     * Method gets all StudentTestDto by id student, id can be null then return all StudentTestDto.
     *
     * @param id student id
     * @return list StudentTestDto.
     */
    @Override
    public List<StudentTestDto> findAllDtoTestStudent(Integer id) {
        return studentTestDtoDao.findNotDoneTestStudentById(id).collect(Collectors.toList());
    }
}
