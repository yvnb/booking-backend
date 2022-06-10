package com.movieportal.booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonRootName("production_country")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductionCountry {
    @JsonProperty("iso_3166_1")
    private String isoCode;
    @JsonProperty("name")
    private String name;
}
