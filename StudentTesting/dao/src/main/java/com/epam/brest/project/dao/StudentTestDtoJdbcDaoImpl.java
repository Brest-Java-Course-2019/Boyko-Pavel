package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.builder.DateBuilder;
import com.epam.brest.project.dao.TestRowMapper.TestRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class StudentTestDtoJdbcDaoImpl implements StudentTestDtoDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentTestDtoJdbcDaoImpl.class);

    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";
    private static final String STUDENT_ID = "student_id";


    @Value("${studentTestDTO.findAllDTO}")
    private String findAllDTO;

    @Value("${studentTestDTO.filterByDate}")
    private String filterByDate;

    @Value("${studentTestDTO.filterNotDoneTestByDate}")
    private String filterNotDoneTestByDate;

    @Value("${studentTestDTO.findAllDtoTestStudentId}")
    private String findAllDtoTestStudentId;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
     * Return  StudentTestDto filtering by date.
     *
     * @return stream StudentTestDto.
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

    @Override
    public Stream<StudentTestDto> findNotDoneTestStudentById(Integer idStudent) {

        LOGGER.warn("start findAllDtoTestStudentId({})", idStudent);

        Map<String, Integer> map = new HashMap<>();
        map.put(STUDENT_ID, idStudent);
        return namedParameterJdbcTemplate.query(findAllDtoTestStudentId,
                map, new TestRowMapper()).stream();
    }

}
