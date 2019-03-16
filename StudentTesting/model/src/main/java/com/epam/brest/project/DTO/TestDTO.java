package com.epam.brest.project.DTO;

import com.epam.brest.project.model.Question;
import com.epam.brest.project.model.QuestionItem;

import java.util.List;

/**
 * Test DTO.
 */
public class TestDTO {

    private Integer idTests;
    private String testName;
    private String subjectName;
    private List<Question> questions;
    private List<List<QuestionItem>> questionItems;

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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<List<QuestionItem>> getQuestionItems() {
        return questionItems;
    }

    public void setQuestionItems(List<List<QuestionItem>> questionItems) {
        this.questionItems = questionItems;
    }

    public TestDTO testName(String testName) {
        this.testName = testName;
        return this;
    }

    public TestDTO idTests(Integer idTests) {
        this.idTests = idTests;
        return this;
    }

    public TestDTO subjectName(String subjectName) {
        this.subjectName = subjectName;
        return this;
    }

    @Override
    public String toString() {
        return "TestDTO{" +
                "idTests=" + idTests +
                ", testName='" + testName + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", questions=" + questions +
                ", questionItems=" + questionItems +
                '}';
    }
}
