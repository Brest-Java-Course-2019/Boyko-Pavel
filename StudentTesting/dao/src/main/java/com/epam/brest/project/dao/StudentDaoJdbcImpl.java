package com.epam.brest.project.dao;

import com.epam.brest.project.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StudentDaoJdbcImpl implements StudentDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${student.selectAllStudent}")
    private String selectAllStudent;

    @Value("${student.selectByStudentLogin}")
    private String selectByStudentLogin;

    @Value("${student.insertStudent}")
    private String insertStudent;

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentTestDtoJdbcDaoImpl.class);

    private final static String STUDENT_LOGIN = "login";
    private final static String STUDENT_PASSWORD = "password";
    private final static String STUDENT_ID = "student_id";
    private final static String STUDENT_NAME = "name";
    private final static String STUDENT_SURNAME = "surname";

    public StudentDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Find all student.
     *
     * @return student stream.
     */
    @Override
    public Stream<Student> findAll() {

        LOGGER.debug("start findAll()");

        List<Student> subjects = namedParameterJdbcTemplate.query(
                selectAllStudent, new StudentRowMapper());
        return subjects.stream();
    }

    /**
     * Find student by login.
     *
     * @param login student login.
     * @return student.
     */
    @Override
    public Optional<Student> findByLogin(final String login) {

        LOGGER.debug("start findById({})", login);

        Student student = namedParameterJdbcTemplate.queryForObject(
                selectByStudentLogin, new MapSqlParameterSource(STUDENT_LOGIN, login), new StudentRowMapper());
        return Optional.ofNullable(student);
    }

    /**
     * Add new student.
     *
     * @param student new student.
     * @return new student.
     */
    @Override
    public Optional<Student> add(Student student) {

        LOGGER.debug("start add({})", student);

        return Optional.of(student).map(this::insertStudent)
                .orElseThrow(() ->
                        new IllegalArgumentException("Student with the " +
                                "this login already exist in DB.subject"));
    }

    /**
     * Add new student.
     *
     * @param student new student.
     * @return new student.
     */
    private Optional<Student> insertStudent(Student student) {

        LOGGER.debug("start insertStudent({})", student);

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(STUDENT_NAME, student.getName());
        mapSqlParameterSource.addValue(STUDENT_SURNAME, student.getSurname());
        mapSqlParameterSource.addValue(STUDENT_LOGIN, student.getLogin());
        mapSqlParameterSource.addValue(STUDENT_PASSWORD, student.getPassword());
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertStudent,
                mapSqlParameterSource, generatedKeyHolder);
        student.setStudentId(generatedKeyHolder.getKey().intValue());
        return Optional.of(student);
    }

    private class StudentRowMapper implements RowMapper<Student> {
        /**
         * @param rs     the RowMapper which creates an object for each row
         * @param rowNum the number of expected rows
         * @return new question
         */
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setStudentId(rs.getInt(STUDENT_ID));
            student.setName(rs.getString(STUDENT_NAME));
            student.setSurname(rs.getString(STUDENT_SURNAME));
            student.setPassword(rs.getString(STUDENT_PASSWORD));
            student.setLogin(rs.getString(STUDENT_LOGIN));
            return student;
        }
    }
}
