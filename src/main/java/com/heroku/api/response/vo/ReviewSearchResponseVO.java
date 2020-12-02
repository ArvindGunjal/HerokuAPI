package com.heroku.api.response.vo;

import java.util.List;

import com.heroku.api.model.MovieModel;

import lombok.Data;

@Data
public class ReviewSearchResponseVO {
	
	private List<MovieModel> reviewModel;

}
