package com.heroku.api.request.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ReviewSearchRequestVO {

	@JsonProperty(value = "review")
	private String review;
	
	@JsonProperty(value = "page_no")
	private String pageNo;
	
	@JsonProperty(value = "page_size")
	private String pageSize;
	
}
