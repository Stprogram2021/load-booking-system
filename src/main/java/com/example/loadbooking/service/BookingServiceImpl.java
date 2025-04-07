package com.example.loadbooking.service;

import com.example.loadbooking.dto.BookingDTO;
import com.example.loadbooking.exception.InvalidOperationException;
import com.example.loadbooking.exception.ResourceNotFoundException;
import com.example.loadbooking.model.Booking;
import com.example.loadbooking.model.BookingStatus;
import com.example.loadbooking.model.Load;
import com.example.loadbooking.model.LoadStatus;
import com.example.loadbooking.repo.BookingRepository;
import com.example.loadbooking.repo.LoadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private LoadRepository loadRepository;

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        logger.info("Creating booking for loadId: {}", bookingDTO.getLoadId());

        Load load = loadRepository.findById(bookingDTO.getLoadId())
                .orElseThrow(() -> new ResourceNotFoundException("Load not found"));

        if (load.getStatus() == LoadStatus.CANCELLED) {
            logger.warn("Attempted to book a cancelled load");
            throw new InvalidOperationException("Cannot book a cancelled load");
        }

        Booking booking = new Booking();
        booking.setLoad(load);
        booking.setTransporterId(bookingDTO.getTransporterId());
        booking.setProposedRate(bookingDTO.getProposedRate());
        booking.setComment(bookingDTO.getComment());
        booking.setRequestedAt(bookingDTO.getRequestedAt());
        booking.setStatus(BookingStatus.PENDING);

        load.setStatus(LoadStatus.BOOKED);
        loadRepository.save(load);

        Booking savedBooking = bookingRepository.save(booking);
        logger.info("Booking created with id: {}", savedBooking.getId());

        return convertToDTO(savedBooking);
    }

    @Override
    public List<BookingDTO> getAllBookings(String transporterId) {
        logger.info("Fetching all bookings");
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public BookingDTO getBookingById(UUID bookingId) {
        logger.info("Fetching booking with id: {}", bookingId);
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        return convertToDTO(booking);
    }

    @Override
    public BookingDTO updateBooking(UUID bookingId, BookingDTO bookingDTO) {
        logger.info("Updating booking with id: {}", bookingId);
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        booking.setProposedRate(bookingDTO.getProposedRate());
        booking.setComment(bookingDTO.getComment());
        booking.setStatus(bookingDTO.getStatus());

        Booking updatedBooking = bookingRepository.save(booking);
        logger.info("Booking updated with id: {}", updatedBooking.getId());

        return convertToDTO(updatedBooking);
    }

    @Override
    public void deleteBooking(UUID bookingId) {
        logger.info("Deleting booking with id: {}", bookingId);
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        Load load = booking.getLoad();
        bookingRepository.deleteById(bookingId);
        load.setStatus(LoadStatus.CANCELLED);
        loadRepository.save(load);

        logger.info("Booking deleted and load status set to CANCELLED");
    }

    private BookingDTO convertToDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setLoadId(booking.getLoad().getId());
        dto.setTransporterId(booking.getTransporterId());
        dto.setProposedRate(booking.getProposedRate());
        dto.setComment(booking.getComment());
        dto.setRequestedAt(booking.getRequestedAt());
        dto.setStatus(booking.getStatus());
        return dto;
    }
}
