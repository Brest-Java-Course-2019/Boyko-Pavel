package com.epam.brest.project.model;


public class StudentAnswer {

    private Integer studentAnswerId;
    private Integer testId;
    private Integer studentId;
    private Integer questionItemId;
    private Boolean studentAnswer;

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public Integer getStudentAnswerId() {
        return studentAnswerId;
    }

    public void setStudentAnswerId(Integer studentAnswerId) {
        this.studentAnswerId = studentAnswerId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getQuestionItemId() {
        return questionItemId;
    }

    public void setQuestionItemId(Integer questionItemId) {
        this.questionItemId = questionItemId;
    }

    public Boolean getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(Boolean studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    @Override
    public String toString() {
        return "StudentAnswer{" +
                "studentAnswerId=" + studentAnswerId +
                ", testId=" + testId +
                ", studentId=" + studentId +
                ", questionItemId=" + questionItemId +
                ", studentAnswer=" + studentAnswer +
                '}';
    }
}
