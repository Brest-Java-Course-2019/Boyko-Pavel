package com.epam.brest.project.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Model class StudentTestDto.
 */
public class StudentTestDto {

    /**
     * The StudentTestDto test ID.
     */
    private Integer idTests;

    /**
     * The StudentTestDto test name.
     */
    private String testName;

    /**
     * The StudentTestDto subject name.
     */
    private String subjectName;

    /**
     * The StudentTestDto teacher id.
     */
    private Integer teacherId;

    /**
     * The StudentTestDto count question.
     */
    private Integer countQuestion;

    /**
     * The StudentTestDto dateOfCreating.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date dateOfCreating;

    /**
     * @return StudentTestDto the idTests.
     */
    public Integer getIdTests() {
        return idTests;
    }

    /**
     * Set StudentTestDto  <code>idTests</code>.
     *
     * @param idTests the StudentTestDto idTests.
     */
    public void setIdTests(Integer idTests) {
        this.idTests = idTests;
    }

    /**
     * @return StudentTestDto the testName.
     */
    public String getTestName() {
        return testName;
    }

    /**
     * Set StudentTestDto  <code>testName</code>.
     *
     * @param testName the StudentTestDto testName.
     */
    public void setTestName(String testName) {
        this.testName = testName;
    }

    /**
     * @return StudentTestDto the subjectName.
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Set StudentTestDto  <code>subjectName</code>.
     *
     * @param subjectName the StudentTestDto subjectName.
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * @return StudentTestDto the countQuestion.
     */
    public Integer getCountQuestion() {
        return countQuestion;
    }

    /**
     * Set StudentTestDto  <code>countQuestion</code>.
     *
     * @param countQuestion the StudentTestDto countQuestion.
     */
    public void setCountQuestion(Integer countQuestion) {
        this.countQuestion = countQuestion;
    }

    /**
     * @return StudentTestDto the dateOfCreating.
     */
    public Date getDateOfCreating() {
        return dateOfCreating;
    }

    /**
     * @return StudentTestDto the idTests.
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * Set StudentTestDto  <code>teacherId</code>.
     *
     * @param teacherId the StudentTestDto teacherId.
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * Set StudentTestDto  <code>dateOfCreting</code>.
     *
     * @param dateOfCreting the StudentTestDto dateOfCreting.
     */
    public void setDateOfCreating(Date dateOfCreting) {
        this.dateOfCreating = dateOfCreting;
    }

    /**
     * Override toString method.
     *
     * @return string which describes the StudentTestDto.
     */
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
