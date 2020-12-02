package com.heroku.api.request.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MovieRequestVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "movie_name")
	private String movieName;
	
	@JsonProperty(value = "release_date")
	private String releaseDate;
	
	@JsonProperty(value = "reviews")
	private List<ReviewRequestVO> reviews;
	
}
