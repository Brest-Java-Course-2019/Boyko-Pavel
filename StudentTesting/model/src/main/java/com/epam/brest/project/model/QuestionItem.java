package com.epam.brest.project.model;

/**
 * Model class QuestionItem.
 */
public class QuestionItem {
    /**
     * The QuestionItem questionItemId.
     */
    private Integer questionItemId;
    /**
     * The QuestionItem description.
     */
    private String description;
    /**
     * The QuestionItem questionId.
     */
    private Integer questionId;
    /**
     * The QuestionItem answer.
     */
    private Boolean answer;

    /**
     * @return QuestionItem the questionItemId.
     */
    public Integer getQuestionItemId() {
        return questionItemId;
    }

    /**
     * Set QuestionItem  <code>questionItemId</code>.
     *
     * @param questionItemId the new QuestionItem questionItemId.
     */
    public void setQuestionItemId(Integer questionItemId) {
        this.questionItemId = questionItemId;
    }

    /**
     * @return QuestionItem the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set QuestionItem  <code>description</code>.
     *
     * @param description the new QuestionItem description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return QuestionItem the questionId.
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * Set QuestionItem  <code>questionId</code>.
     *
     * @param questionId the new QuestionItem questionId.
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    /**
     * @return QuestionItem the questionItems.
     */
    public Boolean getAnswer() {
        return answer;
    }

    /**
     * Set QuestionItem  <code>answer</code>.
     *
     * @param answer the new QuestionItem answer.
     */
    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    /**
     * Override toString method.
     *
     * @return string which describes the QuestionItem.
     */
    @Override
    public String toString() {
        return "QuestionItem{"
                + "questionItemId=" + questionItemId
                + ", description='" + description + '\''
                + ", questionId=" + questionId
                + ", answer=" + answer
                + '}';
    }
}
