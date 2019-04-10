package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.builder.DateBuilder;
import com.epam.brest.project.dao.TestRowMapper.TestRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * StudentTestDtoJdbcDaoImpl implement StudentTestDtoDao.
 */
@Component
public class StudentTestDtoJdbcDaoImpl implements StudentTestDtoDao {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentTestDtoJdbcDaoImpl.class);
    /**
     * Constant fields.
     */
    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";
    private static final String STUDENT_ID = "student_id";

    /**
     * SQL find all TestDTO.
     * type String
     */
    @Value("${studentTestDTO.findAllDTO}")
    private String findAllDTO;
    /**
     * SQL filter TestDTO by date.
     * type String
     */
    @Value("${studentTestDTO.filterByDate}")
    private String filterByDate;
    /**
     * SQL filter not done Test by date.
     * type String
     */
    @Value("${studentTestDTO.filterNotDoneTestByDate}")
    private String filterNotDoneTestByDate;
    /**
     * SQL find all TestDto by Student id.
     * type String
     */
    @Value("${studentTestDTO.findAllDtoTestStudentId}")
    private String findAllDtoTestStudentId;
    /**
     * From property namedParameterJdbcTemplate.
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Create new  QuestionDaoJdbcImpl for the given namedParameterJdbcTemplate.
     *
     * @param namedParameterJdbcTemplate input value.
     */
    public StudentTestDtoJdbcDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Find all StudentTestDto.
     *
     * @return stream StudentTestDto.
     */
    @Override
    public Stream<StudentTestDto> findAllDto() {

        LOGGER.warn("start findAllDto()");

        return namedParameterJdbcTemplate.query(findAllDTO, new TestRowMapper()).stream();
    }

    /**
     * Return all StudentTestDto filtering by date.
     *
     * @param dateBuilder object store startDate and endDate
     * @param studentId   student id
     * @return stream StudentTestDto.
     * @throws ParseException if date is null
     */
    @Override
    public Stream<StudentTestDto> filterByDate(DateBuilder dateBuilder, Integer studentId) throws ParseException {

        LOGGER.warn("start filterByDate({})", dateBuilder);

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(START_DATE, dateBuilder.getTimestampStartDate());
        mapSqlParameterSource.addValue(END_DATE, dateBuilder.getTimestampEndDate());
        mapSqlParameterSource.addValue(STUDENT_ID, studentId);
        return namedParameterJdbcTemplate.query(filterNotDoneTestByDate,
                mapSqlParameterSource, new TestRowMapper()).stream();
    }

    /**
     * Return all StudentTestDto filtering by date.
     *
     * @param idStudent student id
     * @return stream StudentTestDto.
     */
    @Override
    public Stream<StudentTestDto> findNotDoneTestStudentById(Integer idStudent) {

        LOGGER.warn("start findAllDtoTestStudentId({})", idStudent);

        Map<String, Integer> map = new HashMap<>();
        map.put(STUDENT_ID, idStudent);
        return namedParameterJdbcTemplate.query(findAllDtoTestStudentId,
                map, new TestRowMapper()).stream();
    }

}
