package com.epam.brest.project.model;

/**
 * Model class Student.
 */
public class Student {
    /**
     * The Student studentId.
     */
    private Integer studentId;
    /**
     * The Student name.
     */
    private String name;
    /**
     * The Student surname.
     */
    private String surname;
    /**
     * The Student login.
     */
    private String login;
    /**
     * The Student password.
     */
    private String password;

    /**
     * @return Student the studentId.
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * Set Student  <code>studentId</code>.
     *
     * @param studentId the new Student studentId.
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * @return Student the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set Student  <code>name</code>.
     *
     * @param name the new Student name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Student the surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set Student  <code>surname</code>.
     *
     * @param surname the new Student surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return Student the login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set Student  <code>login</code>.
     *
     * @param login the new Student login.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return Student the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set Student  <code>password</code>.
     *
     * @param password the new Student password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Override toString method.
     *
     * @return string which describes the Student.
     */
    @Override
    public String toString() {
        return "Student{"
                + "studentId=" + studentId
                + ", name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
