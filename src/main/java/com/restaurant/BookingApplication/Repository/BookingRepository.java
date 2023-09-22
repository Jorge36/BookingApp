package com.restaurant.BookingApplication.Repository;

import com.restaurant.BookingApplication.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@EnableJpaRepositories
@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>  {

    List<Booking> findAllByBookingDateTimeBetween(LocalDateTime startBookingDateTime, LocalDateTime endBookingDateTime);

    List<Booking> findAllByBookingDateTime(LocalDateTime bookingDateTime);

}
