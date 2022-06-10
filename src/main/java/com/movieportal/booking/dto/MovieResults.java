package com.movieportal.booking.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieResults {
    @JsonProperty("page")
    Integer page;

    @JsonProperty("total_results")
    Long total_results;

    @JsonProperty("total_pages")
    Long total_pages;

    @JsonProperty("results")
    List<Movie> results;
    
    @JsonProperty("status_message")
    String status_message;
    
    @JsonProperty("status_code")
    Integer status_code;    
    
}