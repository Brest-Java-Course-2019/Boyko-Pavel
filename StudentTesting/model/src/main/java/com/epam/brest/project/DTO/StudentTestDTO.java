package com.epam.brest.project.DTO;

import java.util.Date;

public class StudentTestDTO {

    private Integer idTests;
    private String testName;
    private String subjectName;
    private Integer countQuestion;
    private Date dateOfCreating;

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

    public Integer getCountQuestion() {
        return countQuestion;
    }

    public void setCountQuestion(Integer countQuestion) {
        this.countQuestion = countQuestion;
    }

    public Date getDateOfCreating() {
        return dateOfCreating;
    }

    public void setDateOfCreating(Date dateOfCreting) {
        this.dateOfCreating = dateOfCreting;
    }

    @Override
    public String toString() {
        return "StudentTestDTO{" +
                "idTests=" + idTests +
                ", testName='" + testName + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", countQuestion=" + countQuestion +
                ", dateOfCreating=" + dateOfCreating +
                '}';
    }
}
