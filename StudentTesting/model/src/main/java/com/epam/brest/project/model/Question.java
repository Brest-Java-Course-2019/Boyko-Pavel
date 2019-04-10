package com.epam.brest.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class Question.
 */
public class Question {
    /**
     * The Question questionId.
     */
    private Integer questionId;
    /**
     * The Question questionName.
     */
    private String questionName;
    /**
     * The Question testId.
     */
    private Integer testId;
    /**
     * The Question List<QuestionItem>.
     */
    private List<QuestionItem> questionItems = new ArrayList<>();

    /**
     * @return Question the questionItems.
     */
    public List<QuestionItem> getQuestionItems() {
        return questionItems;
    }

    /**
     * Set Question  <code>questionItems</code>.
     *
     * @param questionItems the new Question questionItems.
     */
    public void setQuestionItems(List<QuestionItem> questionItems) {
        this.questionItems = questionItems;
    }

    /**
     * @return Question the questionId.
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * Set Question  <code>questionId</code>.
     *
     * @param questionId the new Question questionId.
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    /**
     * @return Question the questionName.
     */
    public String getQuestionName() {
        return questionName;
    }

    /**
     * Set Question  <code>questionName</code>.
     *
     * @param questionName the new Question questionName.
     */
    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    /**
     * @return Question the testId.
     */
    public Integer getTestId() {
        return testId;
    }

    /**
     * Set Question  <code>testId</code>.
     *
     * @param testId the new Question testId.
     */
    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    /**
     * Override toString method.
     *
     * @return string which describes the Question.
     */
    @Override
    public String toString() {
        return "Question{"
                + "questionId=" + questionId
                + ", questionName='" + questionName + '\''
                + ", testId='" + testId + '\''
                + '}';
    }
}
