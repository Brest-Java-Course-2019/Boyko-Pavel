package com.epam.brest.project.service;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.dao.StudentTestDtoDao;
import com.epam.brest.project.dao.TeacherDao;
import com.epam.brest.project.model.Teacher;
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

    private TeacherDao teacherDao;
    private StudentTestDtoDao studentTestDtoDao;

    public StudentServiceImpl(TeacherDao teacherDao, StudentTestDtoDao studentTestDtoDao) {
        this.teacherDao = teacherDao;
        this.studentTestDtoDao = studentTestDtoDao;
    }

    @Override
    public List<StudentTestDto> findAllDto() {
        LOGGER.debug("Find all student test DTO");
        return studentTestDtoDao.findAllDto().collect(Collectors.toList());
    }

    @Override
    public Teacher findTeacherByLogin (String login) {
        LOGGER.debug("findTeacherByLogin()");
        return teacherDao.findTeacherByLogin(login).get();
    }

    @Override
    public List<StudentTestDto> findAllDtoTestTeacher (Integer id){
        LOGGER.debug("findTeacherByLogin()");
        return teacherDao.findAllDtoTeacher(id).collect(Collectors.toList());
    }
}
