package com.epam.brest.testing.model;

public class QuestionItems {
    private Integer idQuestionItem;
    private String questionItem;


    public Integer getIdQuestionItem() {
        return idQuestionItem;
    }

    public void setIdQuestionItem(Integer idQuestionItem) {
        this.idQuestionItem = idQuestionItem;
    }

    public String getQuestionItem() {
        return questionItem;
    }

    public void setQuestionItem(String questionItem) {
        this.questionItem = questionItem;
    }

    @Override
    public String toString() {
        return "QuestionItems{" +
                "idQuestionItem=" + idQuestionItem +
                ", questionItem='" + questionItem + '\'' +
                '}';
    }
}
