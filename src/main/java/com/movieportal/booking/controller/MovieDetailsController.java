package com.movieportal.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movieportal.booking.dto.Movie;
import com.movieportal.booking.dto.Review;
import com.movieportal.booking.service.MovieDetailsService;


@CrossOrigin(origins = "https://master--movie-portal-yvnb.netlify.app/")
@RestController
@RequestMapping("/api/movie")
public class MovieDetailsController {
	
	@Autowired
	private MovieDetailsService movieDetailsService;
	
	@GetMapping("/featured")
	public ResponseEntity<List<Movie>> getFeaturedMovies() {
		return ResponseEntity.ok(movieDetailsService.getFeaturedMovies());
	}

	@GetMapping("/search")
	public ResponseEntity<List<Movie>> getMoviesBySearchTerm(@RequestParam("query") String searchTerm) {
		return ResponseEntity.ok(movieDetailsService.getMoviesBySearchTerm(searchTerm));
	}
	
	@GetMapping("/{id}/reviews")
	public ResponseEntity<List<Review>> getMovieReviews(@PathVariable("id") Long id) {
		return ResponseEntity.ok(movieDetailsService.getMovieReviews(id));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(movieDetailsService.getMovieDetails(id));
	}
	
}
