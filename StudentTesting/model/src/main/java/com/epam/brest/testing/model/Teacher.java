package com.epam.brest.testing.model;

public class Teacher {

    private String login_teach;
    private String teacher_first_name;
    private String teacher_last_name;
    private String password;

    public String getTeacher_last_name() {
        return teacher_last_name;
    }

    public void setTeacher_last_name(String teacher_last_name) {
        this.teacher_last_name = teacher_last_name;
    }

    public String getLogin_teach() {
        return login_teach;
    }

    public void setLogin_teach(String login_teach) {
        this.login_teach = login_teach;
    }

    public String getTeacher_first_name() {
        return teacher_first_name;
    }

    public void setTeacher_first_name(String teacher_first_name) {
        this.teacher_first_name = teacher_first_name;
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
                "login_teach='" + login_teach + '\'' +
                ", teacher_first_name='" + teacher_first_name + '\'' +
                ", teacher_last_name='" + teacher_last_name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
