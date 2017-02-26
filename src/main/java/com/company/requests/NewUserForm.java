package com.company.requests;

import com.company.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @user LuisTroya
 * @date 26/Feb/2017
 */
public class NewUserForm {
    private Long id;

    @Column(name = "name", length = 100)
    @NotNull(message = "error.name.notnull")
    private String name;

    @Column(name = "lastName", length = 100)
    @NotNull(message = "error.lastName.notnull")
    private String lastName;

    @Column(name = "email", length = 100)
    @NotNull(message = "error.email.notnull")
    private String email;

    @Column(name = "username", length = 100)
    @NotNull(message = "error.username.notnull")
    private String username;

    @Column(name = "password", length = 100)
    @NotNull(message = "error.password.notnull")
    private String password;

    @Enumerated(EnumType.STRING)
    private User.Sex sex;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "country", length = 100)
    @NotNull(message = "error.country.notnull")
    private String country;

    public NewUserForm() {}

    public User toUser() {
        return new User(name, lastName, email, username, password, sex, phone, country);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User.Sex getSex() {
        return sex;
    }

    public void setSex(User.Sex sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format("NewUserForm{id=%d, name='%s', lastName='%s', email='%s', username='%s', password='%s', sex=%s, phone='%s', country='%s'}", id, name, lastName, email, username, password, sex, phone, country);
    }
}
