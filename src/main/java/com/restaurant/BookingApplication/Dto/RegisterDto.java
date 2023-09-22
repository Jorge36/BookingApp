package com.restaurant.BookingApplication.Dto;


import jakarta.persistence.criteria.CriteriaBuilder;

public class RegisterDto {

    private Integer id;
    private String fullName;
    private String email;
    private String mobilePhone;
    private String password;
    private Boolean loggedIn;

    public RegisterDto(Integer id, String fullName, String email, String mobilePhone, String password, Boolean loggedIn) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.password = password;
        this.loggedIn = loggedIn;
    }

    public RegisterDto() {
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
        return "UserDto{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", password='" + password + '\'' +
                ", loggedIn=" + loggedIn +
                '}';
    }
}
