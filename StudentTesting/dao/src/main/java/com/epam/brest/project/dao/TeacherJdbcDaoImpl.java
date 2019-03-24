package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.dao.TestRowMapper.TestRowMapper;
import com.epam.brest.project.model.Teacher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class TeacherJdbcDaoImpl implements TeacherDao {

    private static final String PASSWORD = "password";
    private static final String LOGIN = "login";
    private static final String TEACHER_ID = "teacher_id";
    private static final String TEACHER_NAME = "name";
    private static final String TEACHER_SURNAME = "surname";

    @Value("${teacher.findAllTeacherByLogin}")
    private String findAllTeacherByLogin;

    @Value("${teacher.findAllTestDtoTeacher}")
    private String findAllTestDtoTeacher;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TeacherJdbcDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public Optional<Teacher> findTeacherByLogin(String login){
        Map<String, String> map = new HashMap<>();
        map.put(LOGIN, login);
        return Optional.of(namedParameterJdbcTemplate.queryForObject(
                findAllTeacherByLogin, map, new TeacherRowMapper()));
    }

    @Override
    public Stream<StudentTestDto> findAllDtoTeacher(Integer id) {
        return namedParameterJdbcTemplate.query(
                findAllTestDtoTeacher,
                new MapSqlParameterSource(TEACHER_ID, id), new TestRowMapper())
                .stream();
    }

    private class TeacherRowMapper implements RowMapper<Teacher> {
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
