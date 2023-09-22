package com.restaurant.BookingApplication.Repository;

import com.restaurant.BookingApplication.Entity.TableForBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableForBookingRepository extends JpaRepository<TableForBooking, Integer> {

    List<TableForBooking> findAllByQuantityPeople(Integer quantityPeople);

}
