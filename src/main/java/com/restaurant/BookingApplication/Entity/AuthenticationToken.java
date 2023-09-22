package com.restaurant.BookingApplication.Entity;

import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "authentication_token")
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "token")
    private String token;

    @Column(name = "create_date")
    private Date createDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public AuthenticationToken(User user) {
        this.token = UUID.randomUUID().toString();
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        this.createDate = today.getTime();
        this.user = user;
    }

    public AuthenticationToken(Integer id, String token, Date createDate, User user) {
        this.id = id;
        this.token = token;
        this.createDate = createDate;
        this.user = user;
    }

    public AuthenticationToken() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AuthenticationToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", createDate='" + createDate + '\'' +
                ", user=" + user +
                '}';
    }
}
