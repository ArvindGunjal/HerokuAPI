package com.heroku.api.request.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MovieSearchRequestVO {

	@JsonProperty(value = "movie-name")
	private String movieName;
	
	@JsonProperty(value = "page_no")
	private String pageNo;
	
	@JsonProperty(value = "page_size")
	private String pageSize;
	
}
