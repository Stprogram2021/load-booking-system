package com.example.loadbooking.service;

import com.example.loadbooking.dto.BookingDTO;
import java.util.List;
import java.util.UUID;

public interface BookingService {
    BookingDTO createBooking(BookingDTO bookingDTO);
    List<BookingDTO> getAllBookings(String transporterId);
    BookingDTO getBookingById(UUID bookingId);
    BookingDTO updateBooking(UUID bookingId, BookingDTO bookingDTO);
    void deleteBooking(UUID bookingId);
}
