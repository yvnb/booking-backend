package com.movieportal.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieportal.booking.dto.BookingRequest;
import com.movieportal.booking.model.Booking;
import com.movieportal.booking.service.BookingService;

@CrossOrigin(origins = "https://movie-portal-yvnb.netlify.app")
@RestController
@RequestMapping("/api/booking")
public class BookingController {

	@Autowired
	BookingService bookingService;

	@PostMapping
	public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest bookingRequest) {
		return ResponseEntity.ok(bookingService.createBooking(bookingRequest));
	}

	@GetMapping("/{bookingId}")
	public ResponseEntity<Booking> getBooking(@PathVariable String bookingId) {
		return ResponseEntity.ok(bookingService.getBookingById(bookingId));
	}

	@GetMapping
	public ResponseEntity<List<Booking>> getBookings() {
		return ResponseEntity.ok(bookingService.getBookings());
	}

	@PutMapping("/{bookingId}")
	public ResponseEntity<Booking> updateBooking(@RequestBody BookingRequest bookingRequest,
			@PathVariable String bookingId) {
		return ResponseEntity.ok(bookingService.updateBooking(bookingId, bookingRequest));
	}

	@PatchMapping("/cancel/{bookingId}")
	public ResponseEntity<Booking> cancelBooking(@PathVariable String bookingId) {
		return ResponseEntity.ok(bookingService.cancelBooking(bookingId));
	}

}
