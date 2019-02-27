package com.epam.brest.testing.model;

public class Student {
    private Integer studentId;
    private String studentFirstName;
    private String studentLastName;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    @Override
    public String toString() {
        return "Student{"
                + "studentId=" + studentId
                + ", studentFirstName='" + studentFirstName + '\''
                + ", studentLastName='" + studentLastName + '\''
                + '}';
    }
}
