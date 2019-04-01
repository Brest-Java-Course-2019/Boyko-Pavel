package com.epam.brest.project.dao;

import com.epam.brest.project.model.StudentAnswer;

import java.util.List;


/**
 * Student Answer Dao Interface.
 */
public interface StudentAnswerDao {

    /**
     * Find studentAnswer by student id.
     *
     * @param studentId student id.
     */
    List<StudentAnswer> findStudentAnswerById(Integer studentId);

    /**
     * Add studentAnswer.
     *
     * @param studentAnswer list studentAnswer to add.
     */
    void addStudentAnswer(List<StudentAnswer> studentAnswer);
}
