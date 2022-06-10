package com.movieportal.booking.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Movie {

    @JsonProperty("title")
    private String title;

    @JsonProperty("original_title")
    private String originalTitle;

    @JsonProperty("popularity")
    private float popularity;

    @JsonProperty("backdrop_path")
    private String backdropPath;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("release_date")
  //  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String releaseDate;

    @JsonProperty("adult")
    private boolean adult;

    @JsonProperty("budget")
    private long budget;

    @JsonProperty("genre_ids")
    private List<Long> genreIds;

    @JsonProperty("genres")
    private List<Genre> genres;
    
    @JsonProperty("homepage")
    private String homepage;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("imdb_id")
    private String imdbID;

    @JsonProperty("original_language")
    private String originalLanguage;

    @JsonProperty("production_companies")
    private List<ProductionCompany> productionCompanies;

    @JsonProperty("production_countries")
    private List<ProductionCountry> productionCountries;

    @JsonProperty("revenue")
    private long revenue;

    @JsonProperty("runtime")
    private int runtime;

    @JsonProperty("spoken_languages")
    private List<Language> spokenLanguages;

    @JsonProperty("tagline")
    private String tagline;

    @JsonProperty("rating")
    private float userRating;

    @JsonProperty("vote_average")
    private float voteAverage;

    @JsonProperty("vote_count")
    private int voteCount;

    @JsonProperty("status")
    private String status;

    @JsonProperty("id")
    private Long id;
}