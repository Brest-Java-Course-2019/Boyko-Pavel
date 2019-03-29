package com.epam.brest.project.service;

import com.epam.brest.project.DTO.StudentTestDto;
import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.dao.StudentAnswerDao;
import com.epam.brest.project.dao.StudentTestDtoDao;
import com.epam.brest.project.model.QuestionItem;
import com.epam.brest.project.model.StudentAnswer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements  StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    private StudentTestDtoDao studentTestDtoDao;

    private StudentAnswerDao studentAnswerDao;

    public StudentServiceImpl(StudentTestDtoDao studentTestDtoDao, StudentAnswerDao studentAnswerDao) {
        this.studentTestDtoDao = studentTestDtoDao;
        this.studentAnswerDao = studentAnswerDao;
    }

    @Override
    public List<StudentTestDto> findAllDto() {
        LOGGER.debug("Find all student test DTO");
        return studentTestDtoDao.findAllDto().collect(Collectors.toList());
    }

    @Override
    public List<StudentAnswer> findStudentAnswerById(Integer studentId) {
        return studentAnswerDao.findStudentAnswerById(studentId);
    }

    @Override
    public void addStudentAnswer(TestDto testDto, Integer studentId) {
        List<StudentAnswer> studentList = new ArrayList<>();
        for (List<QuestionItem> list: testDto.getQuestionItems()) {
            for (QuestionItem questionItem:list) {
                StudentAnswer studentAnswer = new StudentAnswer();
                studentAnswer.setQuestionItemId(questionItem.getQuestionItemId());
                studentAnswer.setStudentAnswer(questionItem.getAnswer());
                studentAnswer.setStudentId(studentId);
                studentList.add(studentAnswer);
            }
        }
        studentAnswerDao.addStudentAnswer(studentList);
    }
}
