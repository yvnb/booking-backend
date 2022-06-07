package com.movieportal.booking.model;

import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "bookings")
public class Booking {

	@Id
	private String id;
	private Integer movieId;
	private String movieName;
	private String customerEmail;
	private String customerFirstName;
	private String customerLastName;
	private Instant bookingDate;
	private Integer numberOfSeats;
    private BookingStatus bookingStatus;
	
}
