package com.evil.inc.icresco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email_address", nullable = false)
    private String email;

    public User(final String firstName, final String lastName,
                final String userName, final boolean enabled,
                final String password, final String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.enabled = enabled;
        this.password = password;
        this.email = email;
    }

    protected User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", enabled=" + enabled +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}