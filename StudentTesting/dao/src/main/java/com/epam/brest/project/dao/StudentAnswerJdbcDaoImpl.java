package com.epam.brest.project.dao;

import com.epam.brest.project.model.StudentAnswer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * StudentAnswerJdbcDaoImpl implement StudentAnswerDao.
 */
@Component
public class StudentAnswerJdbcDaoImpl implements StudentAnswerDao {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentAnswerJdbcDaoImpl.class);
    /**
     * Constant fields.
     */
    private static final String QUESTION_ITEM_ID = "question_item_id";
    private static final String STUDENT_ANSWER = "student_answer";
    private static final String STUDENT_ID = "student_id";
    private static final String TEST_ID = "test_id";

    /**
     * SQL select StudentAnswer by id Student.
     * type String
     */
    @Value("${studentAnswer.studentAnswerByIdStudent}")
    private String studentAnswerByIdStudent;
    /**
     * SQL add StudentAnswer.
     * type String
     */
    @Value("${studentAnswer.addStudentAnswer}")
    private String addStudentAnswer;
    /**
     * From property namedParameterJdbcTemplate.
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Create new  QuestionDaoJdbcImpl for the given namedParameterJdbcTemplate.
     *
     * @param namedParameterJdbcTemplate input value.
     */
    public StudentAnswerJdbcDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Find StudentAnswer by Student id.
     *
     * @param studentId Student id.
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
     * Add StudentAnswer.
     *
     * @param studentAnswers list StudentAnswer to add.
     */
    @Override
    public void addStudentAnswer(List<StudentAnswer> studentAnswers) {
        LOGGER.info("addStudentAnswer({})", studentAnswers);
        SqlParameterSource[] sqlParameterSources = new SqlParameterSource[studentAnswers.size()];
        for (int x = 0; x < studentAnswers.size(); x++) {
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue(STUDENT_ANSWER, studentAnswers.get(x).getStudentAnswer());
            mapSqlParameterSource.addValue(QUESTION_ITEM_ID, studentAnswers.get(x).getQuestionItemId());
            mapSqlParameterSource.addValue(TEST_ID, studentAnswers.get(x).getTestId());
            mapSqlParameterSource.addValue(STUDENT_ID, studentAnswers.get(x).getStudentId());
            sqlParameterSources[x] = mapSqlParameterSource;
        }
        namedParameterJdbcTemplate.batchUpdate(addStudentAnswer, sqlParameterSources);
    }

    /**
     * inner StudentAnswerRowMapper implement RowMapper<StudentAnswer>.
     */
    private class StudentAnswerRowMapper implements RowMapper<StudentAnswer> {
        /**
         * @param resultSet the RowMapper which creates an object for each row
         * @param i         the number of expected rows
         * @return new Question
         */
        @Override
        public StudentAnswer mapRow(ResultSet resultSet, int i) throws SQLException {
            StudentAnswer studentAnswer = new StudentAnswer();
            studentAnswer.setQuestionItemId(resultSet.getInt(QUESTION_ITEM_ID));
            studentAnswer.setStudentAnswer(resultSet.getBoolean(STUDENT_ANSWER));
            studentAnswer.setTestId(resultSet.getInt(TEST_ID));
            return studentAnswer;
        }
    }
}
