package com.epam.brest.project.dao;

import com.epam.brest.project.dao.old.StudentDao;
import com.epam.brest.project.model.Student;
import org.springframework.beans.factory.annotation.Value;
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

public class StudetDaoJdbcImpl implements StudentDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${studet.selectAllStudet}")
    private String selectAllStudet;

    @Value("${studet.selectByStudetId}")
    private String selectByStudetId;

    @Value("${studet.insertStudet}")
    private String insertStudet;

    @Value("${studet.updateStudet}")
    private String updateStudet;

    @Value("${studet.deleteStudet}")
    private String deleteStudet;

    private final static String STUDENT_ID = "student_id";
    private final static String STUDENT_NAME = "student_name";
    private final static String STUDENT_SURNAME = "student_surname";

    public StudetDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Stream<Student> findall() {
        List<Student> subjects = namedParameterJdbcTemplate.query(selectAllStudet, BeanPropertyRowMapper.newInstance(Student.class));
        return subjects.stream();
    }

    @Override
    public Optional<Student> findById(Integer id) {
        Student student = namedParameterJdbcTemplate.queryForObject(selectByStudetId, new MapSqlParameterSource(STUDENT_ID, id), (ResultSet rs, int rowNum) -> {
            Student studentNew = new Student();
            studentNew.setStudentId(rs.getInt(STUDENT_ID));
            studentNew.setName(rs.getString(STUDENT_NAME));
            studentNew.setSurname(rs.getString(STUDENT_SURNAME));
            return studentNew;
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
        mapSqlParameterSource.addValue(STUDENT_NAME, student.getName());
        mapSqlParameterSource.addValue(STUDENT_SURNAME, student.getSurname());
        KeyHolder keyHolderGenetator = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertStudet, mapSqlParameterSource, keyHolderGenetator);
        student.setStudentId(keyHolderGenetator.getKey().intValue());
        return Optional.of(student);
    }

    @Override
    public void update(Student student) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(STUDENT_NAME, student.getName());
        mapSqlParameterSource.addValue(STUDENT_SURNAME, student.getSurname());
        Optional.of(namedParameterJdbcTemplate.update(updateStudet, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new NegativeArraySizeException("Failed to update student in DB"));
    }

    @Override
    public void delete(int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(STUDENT_ID, id);
        mapSqlParameterSource.addValue(STUDENT_ID, id);
        Optional.of(namedParameterJdbcTemplate.update(deleteStudet, mapSqlParameterSource))
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
            student.setStudentId(rs.getInt(STUDENT_ID));
            student.setName(rs.getString(STUDENT_NAME));
            student.setSurname(rs.getString(STUDENT_SURNAME));
            return student;
        }
    }
}
