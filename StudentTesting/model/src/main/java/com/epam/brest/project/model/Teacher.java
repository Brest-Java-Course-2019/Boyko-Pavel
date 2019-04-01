package com.epam.brest.project.model;

public class Teacher {
    private Integer teacherId;
    private String teacherName;
    private String surname;
    private String login;
    private String password;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Teacher{"
                + "teacherId=" + teacherId
                + ", teacherName='" + teacherName + '\''
                + ", surname='" + surname + '\''
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
