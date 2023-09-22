package com.restaurant.BookingApplication.Controller;

import com.restaurant.BookingApplication.Dto.BookingDto;
import com.restaurant.BookingApplication.Dto.ResponseDto;
import com.restaurant.BookingApplication.Service.BookingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/bookings")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping(path = "/booking")
    public ResponseEntity<ResponseDto> booking(@RequestBody BookingDto bookingDto, HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        return bookingService.booking(bookingDto, bearerToken);

    }

    @GetMapping(path = "/getAvailableSlots/{date}/{quantityPeople}")
    public ResponseEntity<List<LocalTime>> getAvailableSlots(@PathVariable LocalDate date, @PathVariable Integer quantityPeople, HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        LocalDateTime startBookingDateTime = date.atStartOfDay();
        System.out.println(startBookingDateTime);
        LocalDateTime endBookingDateTime = date.atStartOfDay().plusHours(24);
        System.out.println(endBookingDateTime);

        return bookingService.getAvailableSlots(bearerToken, startBookingDateTime, endBookingDateTime, quantityPeople);

    }

}
