package com.epam.brest.project.DTO;

import com.epam.brest.project.model.Question;
import com.epam.brest.project.model.QuestionItem;

import java.util.ArrayList;
import java.util.List;


/**
 * Test DTO.
 */
public class TestDto {

    private Integer idTests;
    private String testName;
    private String subjectName;
    private Integer subjectId;
    private Integer teacherId;
    private List<Question> questions = new ArrayList<>();
    private List<List<QuestionItem>> questionItems = new ArrayList<>();

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

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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

    public List<Question> addQuestion() {
        return this.questions;
    }

    public List<List<QuestionItem>> addQuestionItem() {
        return this.questionItems;
    }

    public TestDto testName(String testName) {
        this.testName = testName;
        return this;
    }

    public TestDto idTests(Integer idTests) {
        this.idTests = idTests;
        return this;
    }

    public TestDto subjectName(String subjectName) {
        this.subjectName = subjectName;
        return this;
    }

    public TestDto subjectId(Integer subjectId) {
        this.subjectId = subjectId;
        return this;
    }

    public TestDto teacherId(Integer teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    @Override
    public String toString() {
        return "TestDto{" +
                "idTests=" + idTests +
                ", testName='" + testName + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", subjectId=" + subjectId +
                ", teacherId=" + teacherId +
                ", questions=" + questions +
                ", questionItems=" + questionItems +
                '}';
    }
}
