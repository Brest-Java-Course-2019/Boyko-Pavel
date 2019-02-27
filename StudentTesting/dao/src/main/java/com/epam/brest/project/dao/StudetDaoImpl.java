package com.epam.brest.project.dao;

import com.epam.brest.testing.model.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

public class StudetDaoImpl implements StudentDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final static String SELECT_ALL_STUDENT = "SELECT studentId, studentFirstName, studentLastName FROM student";
    private final static String SELECT_BY_ID_STUDENT = "SELECT studentId, studentFirstName, studentLastName FROM student WHERE studentId = :studentId";
    private final static String INSERT = "INSERT INTO student (studentFirstName, studentLastName) VALUES (:studentLastName, :studentLastName)";
    private final static String UPDATE = "UPDATE student SET studentFirstName = :studentFirstName, studentLastName = :studentLastName";
    private final static String DELETE = "DELETE FROM student WHERE studentId = :studentId";
    private final static String ID_STUDENT = "studentId";
    private final static String FIRST_NAME_STUDENT = "studentFirstName";
    private final static String LAST_NAME_STUDENT = "studentLastName";

    public StudetDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Stream<Student> findall() {
        List<Student> subjects = namedParameterJdbcTemplate.query(SELECT_ALL_STUDENT, BeanPropertyRowMapper.newInstance(Student.class));
        return subjects.stream();
    }

    @Override
    public Optional<Student> findById(Integer id) {
        Student student = namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID_STUDENT, new MapSqlParameterSource(ID_STUDENT, id), (ResultSet rs, int rowNum) -> {
            Student student1 = new Student();
            student1.setStudentId(rs.getInt(ID_STUDENT));
            student1.setStudentFirstName(rs.getString(FIRST_NAME_STUDENT));
            student1.setStudentLastName(rs.getString(LAST_NAME_STUDENT));
            return student1;
        });
        return Optional.ofNullable(student);
    }

    @Override
    public Optional<Student> add(Student student) {
        return Optional.of(student).map(this::insertStudent)
                .orElseThrow(() -> new IllegalArgumentException("Student with the this name already exist in DB.subject"));
    }

    private Optional<Student> insertStudent(Student student) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(FIRST_NAME_STUDENT, student.getStudentFirstName());
        mapSqlParameterSource.addValue(LAST_NAME_STUDENT, student.getStudentLastName());
        KeyHolder keyHolderGenetator = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(INSERT, mapSqlParameterSource, keyHolderGenetator);
        student.setStudentId(keyHolderGenetator.getKey().intValue());
        return Optional.of(student);
    }

    @Override
    public void update(Student student) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(FIRST_NAME_STUDENT, student.getStudentFirstName());
        mapSqlParameterSource.addValue(LAST_NAME_STUDENT, student.getStudentLastName());
        Optional.of(namedParameterJdbcTemplate.update(UPDATE, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new NegativeArraySizeException("Failed to update student in DB"));
    }

    @Override
    public void delete(int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(ID_STUDENT, id);
        mapSqlParameterSource.addValue(ID_STUDENT, id);
        Optional.of(namedParameterJdbcTemplate.update(DELETE, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new NegativeArraySizeException("Failed to update student in DB"));

    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    class RowMapperClass implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setStudentId(rs.getInt(ID_STUDENT));
            student.setStudentFirstName(rs.getString(FIRST_NAME_STUDENT));
            student.setStudentLastName(rs.getString(LAST_NAME_STUDENT));
            return student;
        }
    }
}
