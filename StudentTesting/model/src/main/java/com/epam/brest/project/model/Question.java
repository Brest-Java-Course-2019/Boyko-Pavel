package com.epam.brest.project.model;

public class Question {

    private Integer questionId;
    private String questionName;
    private Integer testId;



    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public int getQuestionNameLenght() {
        return getQuestionName().trim().length();
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", questionName='" + questionName + '\'' +
                ", testId='" + testId + '\'' +
                '}';
    }
}
