package com.restaurant.BookingApplication.Service;

import com.restaurant.BookingApplication.Dto.BookingDto;
import com.restaurant.BookingApplication.Dto.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface BookingService {

    ResponseEntity<ResponseDto> booking(BookingDto bookingDto, String bearerToken);

    ResponseEntity<List<LocalTime>> getAvailableSlots(String bearerToken, LocalDateTime startBookingDateTime, LocalDateTime endBookingDateTime, Integer quantityOfEmployers);
}
