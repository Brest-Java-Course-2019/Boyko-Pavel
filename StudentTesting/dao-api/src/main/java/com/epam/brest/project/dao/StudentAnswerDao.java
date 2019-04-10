package com.epam.brest.project.dao;

import com.epam.brest.project.model.StudentAnswer;

import java.util.List;

/**
 * StudentAnswerDao interface.
 */
public interface StudentAnswerDao {

    /**
     * Find StudentAnswer by Student id.
     *
     * @param studentId Student id.
     * @return list StudentAnswer.
     */
    List<StudentAnswer> findStudentAnswerById(Integer studentId);

    /**
     * Add studentAnswer.
     *
     * @param studentAnswer list StudentAnswer to add.
     */
    void addStudentAnswer(List<StudentAnswer> studentAnswer);
}
