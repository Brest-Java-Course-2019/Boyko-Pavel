package com.epam.brest.project.dao.TestRowMapper;

import com.epam.brest.project.DTO.StudentTestDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestRowMapper implements RowMapper<StudentTestDto> {

    private static final String COUNT_QUESTIONS = "countQuestions";
    private static final String ID_TESTS = "test_id";
    private static final String TEST_NAME = "name";
    private static final String SUBJECT_NAME = "subject_name";
    private static final String DATE_OF_CREATING = "created_at";
    private static final String TEACHER_ID = "teacher_id";

        @Override
        public StudentTestDto mapRow(ResultSet resultSet, int i) throws SQLException {
            StudentTestDto studentTestDTO = new StudentTestDto();
            studentTestDTO.setCountQuestion(resultSet.getInt(COUNT_QUESTIONS));
            studentTestDTO.setIdTests(resultSet.getInt(ID_TESTS));
            studentTestDTO.setTestName(resultSet.getString(TEST_NAME));
            studentTestDTO.setSubjectName(resultSet.getString(SUBJECT_NAME));
            studentTestDTO.setDateOfCreating(resultSet.getDate(DATE_OF_CREATING));
            studentTestDTO.setTeacherId(resultSet.getInt(TEACHER_ID));
            return studentTestDTO;
        }
}
