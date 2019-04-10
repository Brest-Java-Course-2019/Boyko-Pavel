package com.epam.brest.project.service;

import com.epam.brest.project.DTO.TestDto;
import com.epam.brest.project.dao.StudentAnswerDao;
import com.epam.brest.project.model.Question;
import com.epam.brest.project.model.QuestionItem;
import com.epam.brest.project.model.StudentAnswer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Class implements StudentAnswerService.
 */
@Service
@Transactional
public class StudentAnswerServiceImpl implements StudentAnswerService {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentAnswerServiceImpl.class);
    /**
     * studentAnswerDao.
     */
    private StudentAnswerDao studentAnswerDao;
    /**
     * Create new  StudentAnswerServiceImpl.
     * @param studentAnswerDao input value.
     */
    public StudentAnswerServiceImpl(StudentAnswerDao studentAnswerDao) {
        this.studentAnswerDao = studentAnswerDao;
    }

    /**
     * Method get all StudentAnswer by student id.
     *
     * @param studentId student id
     * @return List<StudentAnswer>.
     */
    @Override
    public List<StudentAnswer> findStudentAnswerById(Integer studentId) {
        LOGGER.info("findStudentAnswerById({})", studentId);
        return studentAnswerDao.findStudentAnswerById(studentId);
    }

    /**
     * Method add StudentAnswer.
     *
     * @param testDto   stored student answer.
     * @param studentId student id
     */
    @Override
    public void addStudentAnswer(TestDto testDto, Integer studentId) {
        LOGGER.info("addStudentAnswer({},{})", testDto, studentId);
        List<StudentAnswer> studentList = new ArrayList<>();
        for (Question list : testDto.getQuestions()) {
            for (QuestionItem questionItem : list.getQuestionItems()) {
                StudentAnswer studentAnswer = new StudentAnswer();
                studentAnswer.setQuestionItemId(questionItem.getQuestionItemId());
                studentAnswer.setStudentAnswer(questionItem.getAnswer());
                studentAnswer.setStudentId(studentId);
                studentAnswer.setTestId(testDto.getIdTests());
                studentList.add(studentAnswer);
            }
        }
        studentAnswerDao.addStudentAnswer(studentList);
    }
}
