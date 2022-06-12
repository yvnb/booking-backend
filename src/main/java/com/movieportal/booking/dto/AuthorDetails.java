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
public class AuthorDetails {
	@JsonProperty("name")
	public String name;
	@JsonProperty("username")
	public String username;
	@JsonProperty("avatar_path")
	public String avatarPath;
	@JsonProperty("rating")
	public float rating;
}
