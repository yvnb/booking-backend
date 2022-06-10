package com.movieportal.booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonRootName("spoken_language")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Language {


    @JsonProperty("iso_639_1")
    private String isoCode;
    @JsonProperty("name")
    private String name;
}