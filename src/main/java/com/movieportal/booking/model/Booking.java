package com.movieportal.booking.model;

import java.time.Instant;
import java.util.Date;

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
	private String email;
	private String firstName;
	private String lastName;
	private String phone;
	private Date bookingDate;
	private Integer seats;
    private BookingStatus bookingStatus;
	
}
