package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.builder.DateBuilder;
import com.epam.brest.project.dao.TestRowMapper.TestRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class StudentTestDtoJdbcDaoImpl implements StudentTestDtoDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentTestDtoJdbcDaoImpl.class);

//    private static final String COUNT_QUESTIONS = "countQuestions";
//    private static final String ID_TESTS = "test_id";
//    private static final String TEST_NAME = "name";
//    private static final String SUBJECT_NAME = "subject_name";
//    private static final String DATE_OF_CREATING = "created_at";

    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";


    @Value("${studentTestDTO.findAllDTO}")
    private String findAllDTO;

    @Value("${studentTestDTO.filterByDate}")
    private String filterByDate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public StudentTestDtoJdbcDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public Stream<StudentTestDto> findAllDto() {
        return namedParameterJdbcTemplate.query(findAllDTO, new TestRowMapper()).stream();
    }

    @Override
    public Stream<StudentTestDto> filterByDate(DateBuilder dateBuilder) throws ParseException {
        LOGGER.warn("start findallQuestionByTestId()");
        Map<String, Date> map = new HashMap<>();
        map.put(START_DATE, dateBuilder.getTimestampStartDate());
        map.put(END_DATE, dateBuilder.getTimestampEndDate());
        return namedParameterJdbcTemplate.query(filterByDate,
                map, new TestRowMapper()).stream();
    }


//    private class StudentTestDtoRowMapper implements RowMapper<StudentTestDto> {
//        @Override
//        public StudentTestDto mapRow(ResultSet resultSet, int i) throws SQLException {
//            StudentTestDto studentTestDTO = new StudentTestDto();
//            studentTestDTO.setCountQuestion(resultSet.getInt(COUNT_QUESTIONS));
//            studentTestDTO.setIdTests(resultSet.getInt(ID_TESTS));
//            studentTestDTO.setTestName(resultSet.getString(TEST_NAME));
//            studentTestDTO.setSubjectName(resultSet.getString(SUBJECT_NAME));
//            studentTestDTO.setDateOfCreating(resultSet.getDate(DATE_OF_CREATING));
//            return studentTestDTO;
//        }
//    }
}
