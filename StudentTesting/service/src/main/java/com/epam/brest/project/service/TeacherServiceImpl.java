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

/**
 * Class implements TeacherService.
 */
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherServiceImpl.class);
    /**
     * teacherDao.
     */
    private TeacherDao teacherDao;

    /**
     * Create new  TeacherServiceImpl.
     *
     * @param teacherDao input value.
     */
    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    /**
     * Method gets Teacher by login.
     *
     * @param teacher object stored login
     * @return Teacher.
     * @throws EmptyResultDataAccessException if login un correct
     */
    @Override
    public Teacher findTeacherByLogin(Teacher teacher) throws EmptyResultDataAccessException {

        LOGGER.debug("findTeacherByLogin({})", teacher);

        Teacher teacherRespond;
        try {
            teacherRespond = teacherDao.findTeacherByLogin(teacher.getLogin()).get();
        } catch (EmptyResultDataAccessException e) {
            teacherRespond = null;
        }
        return teacherRespond;
    }

    /**
     * Method gets StudentTestDto by teacher id.
     *
     * @param id student id
     * @return list StudentTestDto.
     */
    @Override
    public List<StudentTestDto> findAllDtoTestTeacher(Integer id){

        LOGGER.debug("findAllDtoTestTeacher({})", id);

        return teacherDao.findAllDtoTeacher(id).collect(Collectors.toList());
    }
}
