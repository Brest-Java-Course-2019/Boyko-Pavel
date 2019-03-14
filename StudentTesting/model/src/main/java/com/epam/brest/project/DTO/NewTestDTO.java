package com.epam.brest.project.DTO;

import com.epam.brest.project.model.QuestionItem;

import java.util.List;

/**
 * New Test DTO.
 */
public class NewTestDTO {

    private Integer idTests;
    private String subjectName;
    private String testName;
    private List<List<QuestionItem>> testQuestion;


    public Integer getIdTests() {
        return idTests;
    }

    public void setIdTests(Integer idTests) {
        this.idTests = idTests;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<List<QuestionItem>> getTestQuestion() {
        return testQuestion;
    }

    public void setTestQuestion(List<List<QuestionItem>> testQuestion) {
        this.testQuestion = testQuestion;
    }

    @Override
    public String toString() {
        return "NewTestDTO{" +
                "idTests=" + idTests +
                ", subjectName='" + subjectName + '\'' +
                ", testName='" + testName + '\'' +
                ", testQuestion=" + testQuestion +
                '}';
    }
}
