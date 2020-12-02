package com.heroku.api.request.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ReviewRequestVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "reviewer_name")
	private String reviewerName;
	
	@JsonProperty(value = "rating")
	private String rating;
	
	@JsonProperty(value = "review_comment")
	private String reviewComment;
	
}
