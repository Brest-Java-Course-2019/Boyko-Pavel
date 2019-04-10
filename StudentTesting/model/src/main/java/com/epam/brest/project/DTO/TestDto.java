package com.epam.brest.project.DTO;

import com.epam.brest.project.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class TestDto.
 */
public class TestDto {
    /**
     * The test ID.
     */
    private Integer idTests;
    /**
     * The test name.
     */
    private String testName;
    /**
     * The subject name.
     */
    private String subjectName;
    /**
     * The subject id.
     */
    private Integer subjectId;
    /**
     * The teacher id.
     */
    private Integer teacherId;
    /**
     * The List Question.
     */
    private List<Question> questions = new ArrayList<>();
    /**
     * The array new answer.
     */
    private Boolean[] newAnswer;
    /**
     * The array new question.
     */
    private String[] newQuestion;
    /**
     * The array new description.
     */
    private String[] newDescription;

    /**
     * @return TestDto the newAnswer.
     */
    public Boolean[] getNewAnswer() {
        return newAnswer;
    }

    /**
     * Set TestDto  <code>setNewAnswer</code>.
     *
     * @param newAnswer the new TestDto answer.
     */
    public void setNewAnswer(Boolean[] newAnswer) {
        this.newAnswer = newAnswer;
    }

    /**
     * @return TestDto the newQuestion.
     */
    public String[] getNewQuestion() {
        return newQuestion;
    }

    /**
     * Set TestDto  <code>newQuestion</code>.
     *
     * @param newQuestion the TestDto new question.
     */
    public void setNewQuestion(String[] newQuestion) {
        this.newQuestion = newQuestion;
    }

    /**
     * @return TestDto the newDescription.
     */
    public String[] getNewDescription() {
        return newDescription;
    }

    /**
     * Set TestDto  <code>newDescription</code>.
     *
     * @param newDescription the TestDto new description.
     */
    public void setNewDescription(String[] newDescription) {
        this.newDescription = newDescription;
    }

    /**
     * @return TestDto the idTests.
     */
    public Integer getIdTests() {
        return idTests;
    }

    /**
     * Set TestDto  <code>idTests</code>.
     *
     * @param idTests the TestDto id tests.
     */
    public void setIdTests(Integer idTests) {
        this.idTests = idTests;
    }

    /**
     * @return TestDto the testName.
     */
    public String getTestName() {
        return testName;
    }

    /**
     * Set TestDto  <code>testName</code>.
     *
     * @param testName the TestDto test name.
     */
    public void setTestName(String testName) {
        this.testName = testName;
    }

    /**
     * @return TestDto the subjectName.
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Set TestDto  <code>subjectName</code>.
     *
     * @param subjectName the TestDto subject name.
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * @return TestDto the subjectId.
     */
    public Integer getSubjectId() {
        return subjectId;
    }

    /**
     * Set TestDto  <code>setNewAnswer</code>.
     *
     * @param subjectId the TestDto subject id.
     */
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * @return TestDto the teacherId.
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * Set TestDto  <code>teacherId</code>.
     *
     * @param teacherId the TestDto teacher id.
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return TestDto the questions.
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Set TestDto  <code>List<Question></code>.
     *
     * @param questions the TestDto list questions.
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    /**
     * builder TestDto.
     *
     * @param testName the TestDto test name.
     * @return this TestDto
     */
    public TestDto testName(String testName) {
        this.testName = testName;
        return this;
    }

    /**
     * builder TestDto.
     *
     * @param idTests the TestDto id test.
     * @return this TestDto
     */
    public TestDto idTests(Integer idTests) {
        this.idTests = idTests;
        return this;
    }

    /**
     * builder TestDto.
     *
     * @param subjectName the TestDto subject name.
     * @return this TestDto
     */
    public TestDto subjectName(String subjectName) {
        this.subjectName = subjectName;
        return this;
    }

    /**
     * builder TestDto.
     *
     * @param subjectId the TestDto subject id.
     * @return this TestDto
     */
    public TestDto subjectId(Integer subjectId) {
        this.subjectId = subjectId;
        return this;
    }

    /**
     * builder TestDto.
     *
     * @param teacherId the TestDto teacher id.
     * @return this TestDto
     */
    public TestDto teacherId(Integer teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * Override toString method.
     *
     * @return string which describes the TestDto.
     */
    @Override
    public String toString() {
        return "TestDto{"
                + "idTests=" + idTests
                + ", testName='" + testName + '\''
                + ", subjectName='" + subjectName + '\''
                + ", subjectId=" + subjectId
                + ", teacherId=" + teacherId
                + ", questions=" + questions
                + '}';
    }
}
