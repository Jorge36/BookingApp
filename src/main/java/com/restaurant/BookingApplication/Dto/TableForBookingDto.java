package com.restaurant.BookingApplication.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class TableForBookingDto {

    private Integer id;
    private Integer quantityPeople;

    public TableForBookingDto(Integer id, Integer quantityPeople) {
        this.id = id;
        this.quantityPeople = quantityPeople;
    }

    public TableForBookingDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantityPeople() {
        return quantityPeople;
    }

    public void setQuantityPeople(Integer quantityPeople) {
        this.quantityPeople = quantityPeople;
    }

    @Override
    public String toString() {
        return "TableForBookingDto{" +
                "id=" + id +
                ", quantityPeople=" + quantityPeople +
                '}';
    }
}
