package com.epam.brest.project.model;

public class Question {

    private Integer question_id;
    private String question;
    private String test_id;

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTest_id() {
        return test_id;
    }

    public void setTest_id(String test_id) {
        this.test_id = test_id;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question_id=" + question_id +
                ", question='" + question + '\'' +
                ", test_id='" + test_id + '\'' +
                '}';
    }
}
