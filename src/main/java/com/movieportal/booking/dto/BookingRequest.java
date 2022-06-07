package com.movieportal.booking.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingRequest {

	private String id;
	private Integer movieId;
	private String movieName;
	private String customerEmail;
	private String customerFirstName;
	private String customerLastName;
	private Instant bookingDate;
	private Integer numberOfSeats;
	
}
