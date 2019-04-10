package com.epam.brest.project.model;

/**
 * Model class Teacher.
 */
public class Teacher {
    /**
     * The Teacher teacherId.
     */
    private Integer teacherId;
    /**
     * The Teacher teacherName.
     */
    private String teacherName;
    /**
     * The Teacher surname.
     */
    private String surname;
    /**
     * The Teacher login.
     */
    private String login;
    /**
     * The Teacher password.
     */
    private String password;

    /**
     * @return Teacher the teacherId.
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * Set Teacher  <code>teacherId</code>.
     *
     * @param teacherId the new Teacher teacherId.
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return Teacher the teacherName.
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * Set Teacher  <code>teacherName</code>.
     *
     * @param teacherName the new Teacher teacherName.
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    /**
     * @return Teacher the surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set Teacher  <code>surname</code>.
     *
     * @param surname the new Teacher surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return Teacher the login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set Teacher  <code>login</code>.
     *
     * @param login the new Teacher login.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return Teacher the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set Teacher  <code>password</code>.
     *
     * @param password the new Teacher password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Override toString method.
     *
     * @return string which describes the Teacher.
     */
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
