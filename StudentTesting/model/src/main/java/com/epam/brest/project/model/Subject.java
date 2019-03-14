package com.epam.brest.project.model;

public class Subject {

    private Integer subjectId;
    private String name;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String subjectName) {
        this.name = subjectName;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", Name='" + name + '\'' +
                '}';
    }
}

