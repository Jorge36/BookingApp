package com.restaurant.BookingApplication.Entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="full_name", length = 100)
    private String fullName;

    @Column(name="email", length = 255)
    private String email;

    @Column(name="mobile_phone", length = 15)
    private String mobilePhone;

    @Column(name="password", length = 32)
    private String password;

    @Column(name="logged_in")
    private Boolean loggedIn;

    @OneToMany(mappedBy ="user")
    private Set<Booking> bookings;

    public User(Integer id, String fullName, String email, String mobilePhone, String password, Boolean loggedIn) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.password = password;
        this.loggedIn = loggedIn;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", password='" + password + '\'' +
                ", loggedIn=" + loggedIn +
                '}';
    }

}
