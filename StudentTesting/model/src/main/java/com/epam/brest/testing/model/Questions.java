package com.epam.brest.testing.model;

public class Questions {
    private Integer idTestQuestion;
    private String testQuestion;
    private String subjectIdSubject;

    public Integer getIdTestQuestion() {
        return idTestQuestion;
    }

    public void setIdTestQuestion(Integer idTestQuestion) {
        this.idTestQuestion = idTestQuestion;
    }

    public String getTestQuestion() {
        return testQuestion;
    }

    public void setTestQuestion(String testQuestion) {
        this.testQuestion = testQuestion;
    }

    public String getSubjectIdSubject() {
        return subjectIdSubject;
    }

    public void setSubjectIdSubject(String subjectIdSubject) {
        this.subjectIdSubject = subjectIdSubject;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "idTestQuestion=" + idTestQuestion
                + ", testQuestion='" + testQuestion + '\''
                + ", subjectIdSubject='" + subjectIdSubject + '\''
                + '}';
    }
}
