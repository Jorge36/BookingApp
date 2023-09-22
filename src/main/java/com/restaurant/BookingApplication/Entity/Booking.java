package com.restaurant.BookingApplication.Entity;

import com.restaurant.BookingApplication.Dto.BookingDto;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
public class Booking implements Comparable<Booking> {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "booking_datetime")
    private LocalDateTime bookingDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private TableForBooking tableForBooking;

    public Booking(Integer id, LocalDateTime bookingDateTime, User user, TableForBooking tableForBooking) {
        this.id = id;
        this.bookingDateTime = bookingDateTime;
        this.user = user;
        this.tableForBooking = tableForBooking;
    }

    public Booking() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TableForBooking getTableForBooking() {
        return tableForBooking;
    }

    public void setTableForBooking(TableForBooking tableForBooking) {
        this.tableForBooking = tableForBooking;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", bookingDateTime=" + bookingDateTime +
                ", user=" + user +
                ", table=" + tableForBooking +
                '}';
    }

    @Override
    public int compareTo(Booking booking) {

        if (this.bookingDateTime.isBefore(booking.getBookingDateTime()))
            return -1;
        if (this.bookingDateTime.isAfter(booking.getBookingDateTime()))
            return 1;
        return 0;

    }

}
