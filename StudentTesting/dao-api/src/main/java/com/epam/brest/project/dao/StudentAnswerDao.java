package com.epam.brest.project.dao;

import com.epam.brest.project.model.StudentAnswer;

import java.util.List;


public interface StudentAnswerDao {


    List<StudentAnswer> findStudentAnswerById(Integer studentId);

//    void addStudentAnswer(StudentAnswer studentAnswer);
    void addStudentAnswer(List<StudentAnswer> studentAnswer);
}
