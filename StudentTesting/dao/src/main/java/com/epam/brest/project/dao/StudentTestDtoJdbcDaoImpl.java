package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.dao.TestRowMapper.TestRowMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.stream.Stream;

public class StudentTestDtoJdbcDaoImpl implements StudentTestDtoDao {

    private static final String COUNT_QUESTIONS = "countQuestions";
    private static final String ID_TESTS = "test_id";
    private static final String TEST_NAME = "name";
    private static final String SUBJECT_NAME = "subject_name";
    private static final String DATE_OF_CREATING = "created_at";


    @Value("${studentTestDTO.findAllDTO}")
    private String findAllDTO;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public StudentTestDtoJdbcDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public Stream<StudentTestDto> findAllDto() {
        return namedParameterJdbcTemplate.query(findAllDTO, new TestRowMapper()).stream();
    }

    private class StudentTestDtoRowMapper implements RowMapper<StudentTestDto> {
        @Override
        public StudentTestDto mapRow(ResultSet resultSet, int i) throws SQLException {
            StudentTestDto studentTestDTO = new StudentTestDto();
            studentTestDTO.setCountQuestion(resultSet.getInt(COUNT_QUESTIONS));
            studentTestDTO.setIdTests(resultSet.getInt(ID_TESTS));
            studentTestDTO.setTestName(resultSet.getString(TEST_NAME));
            studentTestDTO.setSubjectName(resultSet.getString(SUBJECT_NAME));
            studentTestDTO.setDateOfCreating(resultSet.getDate(DATE_OF_CREATING));
            return studentTestDTO;
        }
    }
}