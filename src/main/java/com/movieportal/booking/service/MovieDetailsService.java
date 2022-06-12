package com.movieportal.booking.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import com.movieportal.booking.dto.Movie;
import com.movieportal.booking.dto.MovieResults;
import com.movieportal.booking.dto.MovieReviews;
import com.movieportal.booking.dto.Review;

import io.netty.channel.ConnectTimeoutException;
import reactor.util.retry.Retry;

@Service
public class MovieDetailsService {

    @Value("${tmdb.base.url}")
    private String tmdbBaseUrl;
    
    @Value("${tmdb.api.key}")
    private String tmdbAPIKey;    

    @Value("${tmdb.movie.discover.url}")
    private String movieDiscoverUrl;

    @Value("${tmdb.movie.search.url}")
    private String movieSearchUrl;

    @Value("${tmdb.movie.id.url}")
    private String movieIdUrl;
    
    @Value("${tmdb.movie.review.url}")
    private String movieReviewUrl;
    
	@Autowired
	private WebClient webClient;

	public List<Movie> getFeaturedMovies() {		
		
		List<Movie> featuredMovies = new ArrayList<Movie>();		
		MovieResults results = webClient.get()
										.uri(uriBuilder -> uriBuilder
										.path(movieDiscoverUrl)
										.queryParams(getRequestParams())
										.build())
										.retrieve()
										.bodyToMono(new ParameterizedTypeReference<MovieResults>() {
										})
										.retryWhen(Retry.fixedDelay(3, Duration.ofMillis(50)).filter(e -> e instanceof ConnectTimeoutException))
										.block();
		if(results!=null) {
			featuredMovies = results.getResults()
									.stream()
									.sorted(Comparator.comparingDouble(Movie::getPopularity))
									.limit(10)
									.collect(Collectors.toList());
		}
		
		return featuredMovies;
	}
	
	public List<Movie> getMoviesBySearchTerm(String searchTerm) {
		
		List<Movie> matchingMovies = new ArrayList<Movie>();
		MovieResults results = webClient.get()
										.uri(uriBuilder -> uriBuilder
										.path(movieSearchUrl)
										.queryParam("api_key", tmdbAPIKey)
										.queryParam("query", searchTerm)
										.build())
										.retrieve().bodyToMono(new ParameterizedTypeReference<MovieResults>() {
										})
										.retryWhen(Retry.fixedDelay(3, Duration.ofMillis(50)).filter(e -> e instanceof ConnectTimeoutException))
										.block();
		if(results!=null) {
			matchingMovies = results.getResults();
		}
		
		return matchingMovies;
	}
	
	public Movie getMovieDetails(Long movieId) {
		
		Movie movie = webClient.get()
								.uri(uriBuilder -> uriBuilder
								.path(movieIdUrl)
								.queryParam("api_key", tmdbAPIKey)
								.queryParam("language", "en-US")
								.build(movieId))
								.retrieve().bodyToMono(new ParameterizedTypeReference<Movie>() {
								})
								.retryWhen(Retry.fixedDelay(3, Duration.ofMillis(50)).filter(e -> e instanceof ConnectTimeoutException))
								.block();		
		return movie;
	}
	
	public List<Review> getMovieReviews(Long movieId){
		List<Review> movieReviews = new ArrayList<Review>();
		MovieReviews results = webClient.get()
										.uri(uriBuilder -> uriBuilder
										.path(movieReviewUrl)
										.queryParam("api_key", tmdbAPIKey)
										.queryParam("language", "en-US")
										.build(movieId))
										.retrieve().bodyToMono(new ParameterizedTypeReference<MovieReviews>() {
										})
										.retryWhen(Retry.fixedDelay(3, Duration.ofMillis(50)).filter(e -> e instanceof ConnectTimeoutException))
										.block();
		if(results!=null) {
			movieReviews = results.getResults();
		}
		
		return movieReviews;
	}

	private MultiValueMap<String, String> getRequestParams(){
		
		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<String, String>();
		paramMap.add("api_key", tmdbAPIKey);
		paramMap.add("language", "en-US");
		paramMap.add("sort_by", "popularity.desc");
		paramMap.add("include_adult", "false");
		//paramMap.add("page", "1");
		return paramMap;		
	}

}
