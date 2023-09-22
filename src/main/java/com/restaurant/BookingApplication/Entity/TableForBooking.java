package com.restaurant.BookingApplication.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "table_for_booking")
public class TableForBooking {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="quantity_people")
    private Integer quantityPeople;

    @OneToMany(mappedBy ="tableForBooking")
    private Set<Booking> bookings;

    public TableForBooking(Integer id, Integer quantityPeople) {
        this.id = id;
        this.quantityPeople = quantityPeople;
        bookings = new HashSet<>();
    }

    public TableForBooking(Integer id, Integer quantityPeople, Set<Booking> bookings) {
        this.id = id;
        this.quantityPeople = quantityPeople;
        this.bookings = bookings;
    }

    public TableForBooking() {
        bookings = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public Integer getQuantityPeople() {
        return quantityPeople;
    }

    public void setQuantityPeople(Integer quantityPeople) {
        this.quantityPeople = quantityPeople;
    }

    @Override
    public String toString() {
        return "TableForBooking{" +
                "id=" + id +
                ", quantityPeople=" + quantityPeople +
                ", bookings=" + bookings +
                '}';
    }
}
