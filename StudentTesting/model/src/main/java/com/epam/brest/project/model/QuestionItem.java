package com.epam.brest.project.model;

public class QuestionItem {

    private Integer questionItemId;
    private String description;
    private Integer questionId;
    private Boolean answer;

    public Integer getQuestionItemId() {
        return questionItemId;
    }

    public void setQuestionItemId(Integer questionItemId) {
        this.questionItemId = questionItemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuestionItem{" +
                "questionItemId=" + questionItemId +
                ", description='" + description + '\'' +
                ", questionId=" + questionId +
                ", answer=" + answer +
                '}';
    }
}
