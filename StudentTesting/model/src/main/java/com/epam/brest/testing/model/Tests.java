package com.epam.brest.testing.model;

public class Tests {

    private Integer id_tests;
    private String test_name;
    private String teacher_login_teach;
    private String subject_id_subject;
    private String student_id_student;

    public Integer getId_tests() {
        return id_tests;
    }

    public void setId_tests(Integer id_tests) {
        this.id_tests = id_tests;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public String getTeacher_login_teach() {
        return teacher_login_teach;
    }

    public void setTeacher_login_teach(String teacher_login_teach) {
        this.teacher_login_teach = teacher_login_teach;
    }

    public String getSubject_id_subject() {
        return subject_id_subject;
    }

    public void setSubject_id_subject(String subject_id_subject) {
        this.subject_id_subject = subject_id_subject;
    }

    public String getStudent_id_student() {
        return student_id_student;
    }

    public void setStudent_id_student(String student_id_student) {
        this.student_id_student = student_id_student;
    }

    @Override
    public String toString() {
        return "Tests{" +
                "id_tests=" + id_tests +
                ", test_name='" + test_name + '\'' +
                ", teacher_login_teach='" + teacher_login_teach + '\'' +
                ", subject_id_subject='" + subject_id_subject + '\'' +
                ", student_id_student='" + student_id_student + '\'' +
                '}';
    }
}
