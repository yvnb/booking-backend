package com.movieportal.booking.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.movieportal.booking.model.Booking;

public interface BookingRepository extends MongoRepository<Booking, String> {

	List<Booking> findByBookingDateBetween(Instant fromDate, Instant toDate);
}
