package com.epam.brest.testing.model;

public class QuestionItems {
    private Integer id_question_item;
    private String question_item;



    public Integer getId_question_item() {
        return id_question_item;
    }

    public void setId_question_item(Integer id_question_item) {
        this.id_question_item = id_question_item;
    }

    public String getQuestion_item() {
        return question_item;
    }

    public void setQuestion_item(String question_item) {
        this.question_item = question_item;
    }

    @Override
    public String toString() {
        return "QuestionItems{" +
                "id_question_item=" + id_question_item +
                ", question_item='" + question_item + '\'' +
                '}';
    }
}
