package com.epam.brest.project.model;

/**
 * Model class StudentAnswer.
 */
public class StudentAnswer {
    /**
     * The StudentAnswer studentAnswerId.
     */
    private Integer studentAnswerId;
    /**
     * The StudentAnswer testId.
     */
    private Integer testId;
    /**
     * The StudentAnswer studentId.
     */
    private Integer studentId;
    /**
     * The StudentAnswer questionItem id.
     */
    private Integer questionItemId;
    /**
     * The StudentAnswer student answer.
     */
    private Boolean studentAnswer;

    /**
     * @return StudentAnswer the testId.
     */
    public Integer getTestId() {
        return testId;
    }

    /**
     * Set StudentAnswer  <code>testId</code>.
     *
     * @param testId the new StudentAnswer testId.
     */
    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    /**
     * @return StudentAnswer the studentAnswerId.
     */
    public Integer getStudentAnswerId() {
        return studentAnswerId;
    }

    /**
     * Set StudentAnswer  <code>studentAnswerId</code>.
     *
     * @param studentAnswerId the new StudentAnswer studentAnswerId.
     */
    public void setStudentAnswerId(Integer studentAnswerId) {
        this.studentAnswerId = studentAnswerId;
    }

    /**
     * @return StudentAnswer the studentId.
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * Set StudentAnswer  <code>studentId</code>.
     *
     * @param studentId the new StudentAnswer studentId.
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * @return StudentAnswer the questionItemId.
     */
    public Integer getQuestionItemId() {
        return questionItemId;
    }

    /**
     * Set StudentAnswer  <code>questionItemId</code>.
     *
     * @param questionItemId the new StudentAnswer questionItemId.
     */
    public void setQuestionItemId(Integer questionItemId) {
        this.questionItemId = questionItemId;
    }

    /**
     * @return StudentAnswer the studentAnswer.
     */
    public Boolean getStudentAnswer() {
        return studentAnswer;
    }

    /**
     * Set StudentAnswer  <code>studentAnswer</code>.
     *
     * @param studentAnswer the new StudentAnswer studentAnswer.
     */
    public void setStudentAnswer(Boolean studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    /**
     * Override toString method.
     *
     * @return string which describes the StudentAnswer.
     */
    @Override
    public String toString() {
        return "StudentAnswer{"
                + "studentAnswerId=" + studentAnswerId
                + ", testId=" + testId
                + ", studentId=" + studentId
                + ", questionItemId=" + questionItemId
                + ", studentAnswer=" + studentAnswer
                + '}';
    }
}
