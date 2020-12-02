package com.heroku.api.response.vo;

import javax.persistence.Column;

import lombok.Data;

@Data
public class RatingSearchResponseVO {

	@Column(name = "reviewer_name")
	private String reviewerName;
	
	@Column(name = "rating")
	private int rating;

	@Column(name = "review_comment")
	private String reviewerComments;
}
