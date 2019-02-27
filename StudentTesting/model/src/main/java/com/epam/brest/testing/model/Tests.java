package com.epam.brest.testing.model;

public class Tests {

    private Integer idTests;
    private String testName;
    private String teacherloginteach;
    private String subjectIdsubject;
    private String studentIdStudent;

    public Integer getIdTests() {
        return idTests;
    }

    public void setIdTests(Integer idTests) {
        this.idTests = idTests;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTeacherloginteach() {
        return teacherloginteach;
    }

    public void setTeacherloginteach(String teacherloginteach) {
        this.teacherloginteach = teacherloginteach;
    }

    public String getSubjectIdsubject() {
        return subjectIdsubject;
    }

    public void setSubjectIdsubject(String subjectIdsubject) {
        this.subjectIdsubject = subjectIdsubject;
    }

    public String getStudentIdStudent() {
        return studentIdStudent;
    }

    public void setStudentIdStudent(String studentIdStudent) {
        this.studentIdStudent = studentIdStudent;
    }

    @Override
    public String toString() {
        return "Tests{" +
                "idTests=" + idTests +
                ", testName='" + testName + '\'' +
                ", teacherloginteach='" + teacherloginteach + '\'' +
                ", subjectIdsubject='" + subjectIdsubject + '\'' +
                ", studentIdStudent='" + studentIdStudent + '\'' +
                '}';
    }
}
