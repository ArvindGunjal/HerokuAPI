package com.heroku.api.request.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MovieSearchRequestVO {

	@JsonProperty(value = "movie-name")
	private String movieName;
}
