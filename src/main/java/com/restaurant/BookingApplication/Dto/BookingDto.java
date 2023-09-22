package com.restaurant.BookingApplication.Dto;

import com.restaurant.BookingApplication.Entity.TableForBooking;

import java.time.LocalDateTime;

public class BookingDto {

    private Integer id;

    private LocalDateTime createdDate;

    private TableForBookingDto tableForBookingDto;

    public BookingDto(Integer id, LocalDateTime createdDate, TableForBookingDto tableForBookingDto) {
        this.id = id;
        this.createdDate = createdDate;
        this.tableForBookingDto = tableForBookingDto;
    }

    public BookingDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public TableForBookingDto getTableForBookingDto() {
        return tableForBookingDto;
    }

    public void setTableForBookingDto(TableForBookingDto tableForBookingDto) {
        this.tableForBookingDto = tableForBookingDto;
    }

    @Override
    public String toString() {
        return "BookingDto{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", tableForBookingDto=" + tableForBookingDto +
                '}';
    }

}
