package com.epam.brest.project.dao;

import com.epam.brest.project.DTO.NewTestDTO;
import com.epam.brest.project.dao.old.NewTestingDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class NewTestingDaoJdbcImpl implements NewTestingDao {

    @Value("${newTesting.selectAllNewTestDTO}")
    private String selectAllNewTestDTO;

    private static final Logger LOGGER = LoggerFactory.getLogger(NewTestingDaoJdbcImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private NewTestingDaoJdbcImpl (NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    @Override
    public Stream<NewTestDTO> findall() {
        LOGGER.debug("findall{}");
        List<NewTestDTO> newTestDTOS =
                namedParameterJdbcTemplate.query(selectAllNewTestDTO, new RowMapperClass());
        return newTestDTOS.stream();
    }

    @Override
    public Optional<NewTestDTO> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<NewTestDTO> add(NewTestDTO newTesting) {
        return Optional.empty();
    }

    @Override
    public void update(NewTestDTO newTesting) {

    }

    @Override
    public void delete(int id) {

    }

//    private boolean successfullyUpdated(int numRowsUpdated) {
//        return numRowsUpdated > 0;
//    }

    class RowMapperClass implements RowMapper<NewTestDTO> {

        @Override
        public NewTestDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            NewTestDTO newTestDTO = new NewTestDTO();
            newTestDTO.setIdTests(rs.getInt("tests_id"));
            newTestDTO.setSubjectName(rs.getString("subject_name"));
            newTestDTO.setTestName(rs.getString("test_name"));
            newTestDTO.setTestQuestion(rs.getString("question"));
            newTestDTO.setQuestionItems(rs.getString("question_item"));
            newTestDTO.setAnswer(rs.getBoolean("answer"));
            return newTestDTO;
        }
    }
}
