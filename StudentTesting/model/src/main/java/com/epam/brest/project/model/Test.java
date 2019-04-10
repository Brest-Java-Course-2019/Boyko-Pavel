package com.epam.brest.project.model;

/**
 * Model class Test.
 */
public class Test {
    /**
     * The Test testId.
     */
    private Integer testId;
    /**
     * The Test name.
     */
    private String name;
    /**
     * The Test teacherId.
     */
    private Integer teacherId;
    /**
     * The Test subjectName.
     */
    private String subjectName;
    /**
     * The Test subjectId.
     */
    private Integer subjectId;

    /**
     * @return Test the testId.
     */
    public Integer getTestId() {
        return testId;
    }

    /**
     * Set Test  <code>testId</code>.
     *
     * @param testId the new Test testId.
     */
    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    /**
     * @return Test the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set Test  <code>name</code>.
     *
     * @param name the new Test name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Test the teacherId.
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * Set Test  <code>teacherId</code>.
     *
     * @param teacherId the new Test teacherId.
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return Test the subjectName.
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Set Test  <code>subjectName</code>.
     *
     * @param subjectName the new Test subjectName.
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * @return Test the subjectId.
     */
    public Integer getSubjectId() {
        return subjectId;
    }

    /**
     * Set Test  <code>subjectId</code>.
     *
     * @param subjectId the new Test subjectId.
     */
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * Override toString method.
     *
     * @return string which describes the Test.
     */
    @Override
    public String toString() {
        return "Test{"
                + "testId=" + testId
                + ", name='" + name + '\''
                + ", teacherId=" + teacherId
                + ", subjectName='" + subjectName + '\''
                + ", subjectId=" + subjectId
                + '}';
    }
}
