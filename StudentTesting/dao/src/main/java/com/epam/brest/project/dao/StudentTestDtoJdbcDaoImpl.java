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
     * @return stream StudentTestDto.
     */
    @Override
    public Stream<StudentTestDto> filterByDate(DateBuilder dateBuilder) throws ParseException {
        LOGGER.warn("start filterByDate()");
        Map<String, Date> map = new HashMap<>();
        map.put(START_DATE, dateBuilder.getTimestampStartDate());
        map.put(END_DATE, dateBuilder.getTimestampEndDate());
        return namedParameterJdbcTemplate.query(filterByDate,
                map, new TestRowMapper()).stream();
    }
}
