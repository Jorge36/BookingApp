package com.restaurant.BookingApplication.Service.Implementation;

import com.restaurant.BookingApplication.Controller.Status;
import com.restaurant.BookingApplication.Dto.BookingDto;
import com.restaurant.BookingApplication.Dto.ResponseDto;
import com.restaurant.BookingApplication.Dto.TableForBookingDto;
import com.restaurant.BookingApplication.Entity.AuthenticationToken;
import com.restaurant.BookingApplication.Entity.Booking;
import com.restaurant.BookingApplication.Entity.TableForBooking;
import com.restaurant.BookingApplication.Entity.User;
import com.restaurant.BookingApplication.Exception.CustomException;
import com.restaurant.BookingApplication.Repository.AuthenticationTokenRepository;
import com.restaurant.BookingApplication.Repository.BookingRepository;
import com.restaurant.BookingApplication.Repository.TableForBookingRepository;
import com.restaurant.BookingApplication.Repository.UserRepository;
import com.restaurant.BookingApplication.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AuthenticationTokenRepository authenticationTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TableForBookingRepository tableForBookingRepository;

    @Override
    public ResponseEntity<ResponseDto> booking(BookingDto bookingDto, String bearerToken) {

        System.out.println(bookingDto);

        // verify token exists and belongs to a user who is logged in
        AuthenticationToken authenticationToken = authenticationTokenRepository.findByToken(bearerToken);

        if (Objects.isNull(authenticationToken)) {

            throw new CustomException("Token does not exist");

        }

        Optional<User> user = userRepository.findById(authenticationToken.getUser().getId());

        if (!user.isPresent()) {

            throw new CustomException("User does not exist");

        }

        if (user.get().getLoggedIn().equals(Boolean.FALSE)) {

            throw new CustomException("User is not logged in");

        }

        TableForBooking tableForBooking = new TableForBooking();

        if (bookingDto.getTableForBookingDto().getId() != null) {

            // create table for booking
            tableForBooking.setId(bookingDto.getTableForBookingDto().getId());
            tableForBooking.setQuantityPeople(bookingDto.getTableForBookingDto().getQuantityPeople());

        } else {

            // get table for booking
            List<Booking> bookings = bookingRepository.findAllByBookingDateTime(bookingDto.getCreatedDate());

            List<TableForBooking> tableForBookings = tableForBookingRepository.findAllByQuantityPeople(bookingDto.getTableForBookingDto().getQuantityPeople());

            ListIterator<TableForBooking> iter;
            // get a table for the customer
            for(Booking b: bookings) {
                iter = tableForBookings.listIterator();
                // remove tables which are already booked
                while (iter.hasNext()) {
                    if (b.getTableForBooking().getId() == iter.next().getId())
                        iter.remove();

                }
            }

            if (tableForBookings.isEmpty())

                throw new CustomException("Slot was taken, sorry for any inconvenient");

            else {

                tableForBooking.setId(tableForBookings.get(0).getId());
                tableForBooking.setQuantityPeople(tableForBookings.get(0).getQuantityPeople());

            }

        }

        // create booking
        Booking booking = new Booking(

                bookingDto.getId(),
                bookingDto.getCreatedDate(),
                user.get(),
                tableForBooking
        );

        tableForBooking.addBooking(booking);

        // save booking
        bookingRepository.save(booking);

        return new ResponseEntity(new ResponseDto(Status.SUCCESS, "booking created successfully"), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<LocalTime>> getAvailableSlots(String bearerToken, LocalDateTime startBookingDateTime, LocalDateTime endBookingDateTime, Integer quantityPeople) {

        // constants
        final LocalTime startBooking = LocalTime.of( 12,00,00,00 );
        final LocalTime endBooking = LocalTime.of( 22,00,00,00 );

        final Integer bookingGap = 2;

        List<LocalTime> slots = new LinkedList<>();
        Hashtable<LocalTime, Integer> slotTables = new Hashtable<LocalTime, Integer>();


        // verify token exists and belongs to a user who is logged in
        AuthenticationToken authenticationToken = authenticationTokenRepository.findByToken(bearerToken);

        if (Objects.isNull(authenticationToken)) {

            throw new CustomException("Token does not exist");

        }

        // check how many tables for that quantity we have in the restaurant
        List<TableForBooking> tableForBookings = tableForBookingRepository.findAll();
        Integer quantityTables = 0;
        for(TableForBooking t: tableForBookings) {
            if (t.getQuantityPeople() == quantityPeople) {
                quantityTables++;
            }
        }
        // there are no table for that quantity of people
        if (quantityTables == 0) {

            throw new CustomException("No availability for that day");

        }
        // Create List of times and hash table with the quantity of tables per slot
        LocalTime bookingsTime = startBooking;

        while(!bookingsTime.equals(endBooking)) {

            slots.add(bookingsTime);
            slotTables.put(bookingsTime, quantityTables);
            bookingsTime = bookingsTime.plusHours(bookingGap);

        }

        List<Booking> bookings = bookingRepository.findAllByBookingDateTimeBetween(startBookingDateTime, endBookingDateTime);
        Collections.sort(bookings);

        for (LocalTime s: slots) { // slots

            for (Booking b: bookings) { // bookings

                if (s.equals(b.getBookingDateTime().toLocalTime())) { // time in the slot is equal to the booking, check if the booking is for the same quantity of people

                    if (b.getTableForBooking().getQuantityPeople() == quantityPeople) {
                        // decrement one, there is already a booking same quantity of people
                        slotTables.put(s, slotTables.get(s) - 1);

                    }

                }
            }

        }

        ListIterator<LocalTime> iter = slots.listIterator();
        // remove slots which does not have availability
        while (iter.hasNext()) {
            if (slotTables.get(iter.next()) <= 0)
                iter.remove();

        }

        return new ResponseEntity(slots, HttpStatus.OK);
    }





}
