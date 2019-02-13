package com.epam.test.model;

public class Questions {
    private Integer id_test_question;
    private String test_question;
    private String subject_id_subject;

    public Integer getId_test_question() {
        return id_test_question;
    }

    public void setId_test_question(Integer id_test_question) {
        this.id_test_question = id_test_question;
    }

    public String getTest_question() {
        return test_question;
    }

    public void setTest_question(String test_question) {
        this.test_question = test_question;
    }

    public String getSubject_id_subject() {
        return subject_id_subject;
    }

    public void setSubject_id_subject(String subject_id_subject) {
        this.subject_id_subject = subject_id_subject;
    }
}
