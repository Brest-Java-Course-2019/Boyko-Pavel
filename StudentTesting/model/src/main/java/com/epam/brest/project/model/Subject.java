package com.epam.brest.project.model;

/**
 * Model class Subject.
 */
public class Subject {
    /**
     * The Subject subjectId.
     */
    private Integer subjectId;
    /**
     * The Subject name.
     */
    private String name;

    /**
     * @return Subject the subjectId.
     */
    public Integer getSubjectId() {
        return subjectId;
    }

    /**
     * Set Subject  <code>subjectId</code>.
     *
     * @param subjectId the new Subject subjectId.
     */
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * @return Subject the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set Subject  <code>name</code>.
     *
     * @param name the new Subject name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Override toString method.
     *
     * @return string which describes the Subject.
     */
    @Override
    public String toString() {
        return "Subject{"
                + "subjectId=" + subjectId
                + ", Name='" + name + '\''
                + '}';
    }
}

