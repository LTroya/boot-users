package com.company.entities;

import com.company.views.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.CircuitBreaker;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @user siti2017
 * @date 14/02/17
 */
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "users_unique_username_idx", columnNames = "username"),
        @UniqueConstraint(name = "users_unique_email_idx", columnNames = "email")
})
public class User extends BaseEntity {
    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    public enum Sex {MALE, FEMALE}

    @Id
    @GeneratedValue
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "country", length = 100)
    @NotNull(message = "error.country.notnull")
    private String country;

    public User() {
    }

    public User(String name, String lastName, String email, String username, String password, Sex sex, String phone, String country) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.country = country;
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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
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
        return String.format("User{id=%d, name='%s', lastName='%s', email='%s', username='%s', password='%s', sex='%s', phone='%s', country='%s', createdAt=%s, updatedAt=%s}", id, name, lastName, email, username, password, sex, phone, country, super.createdAt, super.updatedAt);
    }
}
