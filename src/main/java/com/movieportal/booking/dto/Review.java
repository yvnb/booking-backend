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
public class Review {
	
	@JsonProperty("author")
	public String author;
	@JsonProperty("author_details")
	public AuthorDetails authorDetails;
	@JsonProperty("content")
	public String content;
	@JsonProperty("created_at")
	public String createdAt;
	@JsonProperty("id")
	public String id;
	@JsonProperty("updated_at")
	public String updatedAt;
	@JsonProperty("url")
	public String url;

}
