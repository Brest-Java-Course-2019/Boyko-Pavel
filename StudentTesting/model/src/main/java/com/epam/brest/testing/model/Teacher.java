package com.epam.brest.testing.model;

public class Teacher {

    private String loginTeach;
    private String teacherFirstName;
    private String teacherLastName;
    private String password;

    public String getLoginTeach() {
        return loginTeach;
    }

    public void setLoginTeach(String loginTeach) {
        this.loginTeach = loginTeach;
    }

    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "loginTeach='" + loginTeach + '\'' +
                ", teacherFirstName='" + teacherFirstName + '\'' +
                ", teacherLastName='" + teacherLastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
