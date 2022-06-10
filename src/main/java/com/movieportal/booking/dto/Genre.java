package com.movieportal.booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Genre {
	
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
}
