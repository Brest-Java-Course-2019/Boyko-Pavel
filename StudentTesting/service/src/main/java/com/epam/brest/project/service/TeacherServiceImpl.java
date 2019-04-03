package com.epam.brest.project.service;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.dao.TeacherDao;
import com.epam.brest.project.model.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeacherServiceImpl implements  TeacherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherServiceImpl.class);

    private TeacherDao teacherDao;

    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public Teacher findTeacherByLogin (String login) {

        LOGGER.debug("findTeacherByLogin({})", login);

        return teacherDao.findTeacherByLogin(login).get();
    }



    @Override
    public List<StudentTestDto> findAllDtoTestTeacher (Integer id)
            throws EmptyResultDataAccessException {

        LOGGER.debug("findAllDtoTestTeacher({})", id);

        return teacherDao.findAllDtoTeacher(id).collect(Collectors.toList());
    }
}
