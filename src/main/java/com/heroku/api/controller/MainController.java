package com.heroku.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.heroku.api.request.vo.MovieDeleteRequestVO;
import com.heroku.api.request.vo.MovieRequestVO;
import com.heroku.api.request.vo.MovieSearchRequestVO;
import com.heroku.api.request.vo.ReviewSearchRequestVO;
import com.heroku.api.response.vo.CommonResponseEntity;
import com.heroku.api.service.MainService;

@RestController
public class MainController {
	
	@Autowired
	private MainService mainServie;
	
	@GetMapping("/getMessage")
	public String getMessageArvind() {
		
//		mainServie.saveMovieRecord();
		return "Hello Arvind";
		
	}

	@GetMapping("/")
	public String getMessage() {
		
		return "Hello World";
		
	}
	
	@PostMapping(path = "/save-movie")
	public ResponseEntity<CommonResponseEntity> saveMovie(@RequestBody MovieRequestVO movieRequest) {
	
		CommonResponseEntity cre = mainServie.saveMovieRecord(movieRequest);
		
		return new ResponseEntity<CommonResponseEntity>(cre, HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/delete-movie")
	public ResponseEntity<CommonResponseEntity> deleteMovie(@RequestBody MovieDeleteRequestVO movieDeleteRequest) {
	
		CommonResponseEntity cre = mainServie.deleteMovie(movieDeleteRequest);
		
		return new ResponseEntity<CommonResponseEntity>(cre, HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/search-movie")
	public ResponseEntity<CommonResponseEntity> searchMovie(@RequestBody MovieSearchRequestVO movieSearchRequest) {
	
		CommonResponseEntity cre = mainServie.searchMovie(movieSearchRequest);
		
		return new ResponseEntity<CommonResponseEntity>(cre, HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/search-comment")
	public ResponseEntity<CommonResponseEntity> searchReview(@RequestBody ReviewSearchRequestVO reviewSearchRequest) {
	
		CommonResponseEntity cre = mainServie.searchByComments(reviewSearchRequest);
		
		return new ResponseEntity<CommonResponseEntity>(cre, HttpStatus.OK);
		
	}
	
	
	
}
	
	

