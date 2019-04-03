package com.epam.brest.project.dao;

import com.epam.brest.project.model.StudentAnswer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentAnswerJdbcDaoImpl implements StudentAnswerDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentAnswerJdbcDaoImpl.class);

    private static final String QUESTION_ITEM_ID = "question_item_id";
    private static final String STUDENT_ANSWER = "student_answer";
    private static final String STUDENT_ID = "student_id";


    @Value("${studentAnswer.studentAnswerByIdStudent}")
    private String studentAnswerByIdStudent;

    @Value("${studentAnswer.addStudentAnswer}")
    private String addStudentAnswer;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public StudentAnswerJdbcDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Find studentAnswer by student id.
     *
     * @param studentId student id.
     */
    @Override
    public List<StudentAnswer> findStudentAnswerById(Integer studentId) {
        LOGGER.info("findStudentAnswerByIdStudent({})", studentId);
        Map<String, Integer> map = new HashMap<>();
        map.put(STUDENT_ID, studentId);
        return namedParameterJdbcTemplate.query(studentAnswerByIdStudent,
                map, new StudentAnswerRowMapper());
    }

    /**
     * Add studentAnswer.
     *
     * @param studentAnswers list studentAnswer to add.
     */
    @Override
    public void addStudentAnswer(List<StudentAnswer> studentAnswers) {
        LOGGER.info("addStudentAnswer({})", studentAnswers);
        SqlParameterSource[] sqlParameterSources = new SqlParameterSource[studentAnswers.size()];
        for (int x = 0; x < studentAnswers.size(); x++) {
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue(
                    STUDENT_ANSWER, studentAnswers.get(x).getStudentAnswer());
            mapSqlParameterSource.addValue(
                    QUESTION_ITEM_ID, studentAnswers.get(x).getQuestionItemId());
            mapSqlParameterSource.addValue(STUDENT_ID, studentAnswers.get(x).getStudentId());
            sqlParameterSources[x] = mapSqlParameterSource;
        }
        namedParameterJdbcTemplate.batchUpdate(addStudentAnswer, sqlParameterSources);
    }

    private class StudentAnswerRowMapper implements RowMapper<StudentAnswer> {
        /**
         * @param resultSet the RowMapper which creates an object for each row
         * @param i         the number of expected rows
         * @return new question
         */
        @Override
        public StudentAnswer mapRow(ResultSet resultSet, int i) throws SQLException {
            StudentAnswer studentAnswer = new StudentAnswer();
            studentAnswer.setQuestionItemId(resultSet.getInt(QUESTION_ITEM_ID));
            studentAnswer.setStudentAnswer(resultSet.getBoolean(STUDENT_ANSWER));
            return studentAnswer;
        }
    }
}
