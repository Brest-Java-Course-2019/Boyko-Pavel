package com.epam.brest.project.dao;

import com.epam.brest.project.model.Student;
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

    @Value("${student.selectByStudentId}")
    private String selectByStudentId;

    @Value("${student.insertStudent}")
    private String insertStudent;


    private final static String STUDENT_ID = "student_id";
    private final static String STUDENT_NAME = "name";
    private final static String STUDENT_SURNAME = "surname";

    public StudentDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Stream<Student> findAll() {
        List<Student> subjects = namedParameterJdbcTemplate.query(selectAllStudent, new StudentRowMapper());
        return subjects.stream();
    }

    @Override
    public Optional<Student> findById(Integer id) {
        Student student = namedParameterJdbcTemplate.queryForObject(selectByStudentId, new MapSqlParameterSource(STUDENT_ID, id), (ResultSet rs, int rowNum) -> {
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
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertStudent, mapSqlParameterSource, generatedKeyHolder);
        System.out.println("434");
        student.setStudentId(generatedKeyHolder.getKey().intValue());
        return Optional.of(student);
    }

    private class StudentRowMapper implements RowMapper<Student> {

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
