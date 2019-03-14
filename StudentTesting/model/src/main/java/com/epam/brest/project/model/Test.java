package com.epam.brest.project.model;

public class Test {

    private Integer test_id;
    private String name;
    private String teacher_id;
    private String subject_id;

    public Integer getTest_id() {
        return test_id;
    }

    public void setTest_id(Integer test_id) {
        this.test_id = test_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    @Override
    public String toString() {
        return "Test{" +
                "test_id=" + test_id +
                ", name='" + name + '\'' +
                ", teacher_id='" + teacher_id + '\'' +
                ", subject_id='" + subject_id + '\'' +
                '}';
    }
}
