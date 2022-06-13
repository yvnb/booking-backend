package com.movieportal.booking.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.movieportal.booking.dto.BookingRequest;
import com.movieportal.booking.exception.EntityNotFoundException;
import com.movieportal.booking.model.Booking;
import com.movieportal.booking.model.BookingStatus;
import com.movieportal.booking.repository.BookingRepository;

@Service
public class BookingService {

	@Autowired
	BookingRepository bookingRepository;

	public Booking createBooking(BookingRequest bookingRequest) {
		/*
		 * Optional<Booking> booking =
		 * bookingRepository.findById(bookingRequest.getId()); if (booking.isPresent())
		 * { throw new EntityNotFoundException("Author Not Found"); }
		 */
		Booking newBooking = new Booking();
		BeanUtils.copyProperties(bookingRequest, newBooking);
		newBooking.setBookingStatus(BookingStatus.BOOKED);
		return bookingRepository.save(newBooking);
	}

	public Booking getBookingById(String id) {
		Optional<Booking> booking = bookingRepository.findById(id);
		if (booking.isPresent()) {
			return booking.get();
		}
		throw new EntityNotFoundException("Cant find any booking under given ID");
	}
	
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }
    
    public Booking updateBooking(String id, BookingRequest bookingRequest) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (!optionalBooking.isPresent()) {
            throw new EntityNotFoundException("Booking not present in the database");
        }
        Booking booking = optionalBooking.get();
        if(bookingRequest.getFirstName()!=null) {
        	booking.setFirstName(bookingRequest.getFirstName());
        }
        if(bookingRequest.getLastName()!=null) {
        	booking.setLastName(bookingRequest.getLastName());
        }
        if(bookingRequest.getSeats()!=null) {
        	booking.setSeats(bookingRequest.getSeats());
        }
        if(bookingRequest.getBookingDate()!=null) {
        	booking.setBookingDate(bookingRequest.getBookingDate());
        }
        if(bookingRequest.getPhone()!=null) {
        	booking.setPhone(bookingRequest.getPhone());
        }
        if(bookingRequest.getEmail()!=null) {
        	booking.setEmail(bookingRequest.getEmail());
        }
        if(bookingRequest.getBookingStatus()!=null) {
        	booking.setBookingStatus(bookingRequest.getBookingStatus());
        }
        return bookingRepository.save(booking);
    }
    
    public Booking cancelBooking(String id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (!optionalBooking.isPresent()) {
            throw new EntityNotFoundException("Booking not present in the database");
        }
        Booking booking = optionalBooking.get();
        booking.setBookingStatus(BookingStatus.CANCELLED);
        return bookingRepository.save(booking);
    }
    
    public List<Booking> getBookingBySearchTerm(String freeText){
    	return null;
    }

    public List<Booking> getBookingByDateRange(Instant fromDate, Instant toDate){
    	return bookingRepository.findByBookingDateBetween(fromDate, toDate);
    }
    
}
