package com.heroku.api.response.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MovieSearchResponseVO {

	@JsonProperty(value = "movie_name")
	private String movieName;
	
	@JsonProperty(value = "release_date")
	private String releaseDate;
	
	@JsonProperty(value = "average_ratings")
	private String avgRating;
	
	@JsonProperty(value = "reviews")
	private List<RatingSearchResponseVO> reviews;
	
}

