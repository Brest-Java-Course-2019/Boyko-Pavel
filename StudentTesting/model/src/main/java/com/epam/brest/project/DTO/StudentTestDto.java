package com.epam.brest.project.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class StudentTestDto {

    private Integer idTests;
    private String testName;
    private String subjectName;
    private Integer teacherId;
    private Integer countQuestion;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
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

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public void setDateOfCreating(Date dateOfCreting) {
        this.dateOfCreating = dateOfCreting;
    }

    @Override
    public String toString() {
        return "StudentTestDto{"
                + "idTests=" + idTests
                + ", testName='" + testName + '\''
                + ", subjectName='" + subjectName + '\''
                + ", teacherId=" + teacherId
                + ", countQuestion=" + countQuestion
                + ", dateOfCreating=" + dateOfCreating
                + '}';
    }
}
