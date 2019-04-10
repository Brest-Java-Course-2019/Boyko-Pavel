package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.dao.TestRowMapper.TestRowMapper;
import com.epam.brest.project.model.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * TeacherJdbcDaoImpl implement TeacherDao.
 */
@Component
public class TeacherJdbcDaoImpl implements TeacherDao {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherJdbcDaoImpl.class);
    /**
     * Constant fields.
     */
    private static final String PASSWORD = "password";
    private static final String LOGIN = "login";
    private static final String TEACHER_ID = "teacher_id";
    private static final String TEACHER_NAME = "name";
    private static final String TEACHER_SURNAME = "surname";
    /**
     * SQL find all Teacher by login.
     * type String
     */
    @Value("${teacher.findAllTeacherByLogin}")
    private String findAllTeacherByLogin;
    /**
     * SQL find all TestDto by id Teacher.
     * type String
     */
    @Value("${teacher.findAllTestDtoTeacher}")
    private String findAllTestDtoTeacher;
    /**
     * From property namedParameterJdbcTemplate.
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Create new  QuestionDaoJdbcImpl for the given namedParameterJdbcTemplate.
     *
     * @param namedParameterJdbcTemplate input value.
     */
    public TeacherJdbcDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Find all teacher by teacher login.
     *
     * @param login login teacher.
     * @return teacher by login.
     */
    @Override
    public Optional<Teacher> findTeacherByLogin(String login) throws EmptyResultDataAccessException {

        LOGGER.debug("findTeacherByLogin({})", login);

        Map<String, String> map = new HashMap<>();
        map.put(LOGIN, login);
        return Optional.of(namedParameterJdbcTemplate.queryForObject(
                findAllTeacherByLogin, map, new TeacherRowMapper()));
    }

    /**
     * Find all StudentTestDto by teacher id.
     *
     * @param id id teacher
     * @return StudentTestDto stream.
     */
    @Override
    public Stream<StudentTestDto> findAllDtoTeacher(Integer id) {

        LOGGER.debug("findAllDtoTeacher({})", id);

        return namedParameterJdbcTemplate.query(
                findAllTestDtoTeacher,
                new MapSqlParameterSource(TEACHER_ID, id), new TestRowMapper())
                .stream();
    }

    /**
     * inner TeacherRowMapper implement RowMapper<Teacher>.
     */
    private class TeacherRowMapper implements RowMapper<Teacher> {
        /**
         * @param resultSet the RowMapper which creates an object for each row
         * @param i         the number of expected rows
         * @return new question
         */
        @Override
        public Teacher mapRow(ResultSet resultSet, int i) throws SQLException {
            Teacher teacher = new Teacher();
            teacher.setTeacherId(resultSet.getInt(TEACHER_ID));
            teacher.setTeacherName(resultSet.getString(TEACHER_NAME));
            teacher.setSurname(resultSet.getString(TEACHER_SURNAME));
            teacher.setPassword(resultSet.getString(PASSWORD));
            teacher.setLogin(resultSet.getString(LOGIN));
            return teacher;
        }
    }
}
