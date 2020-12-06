package com.heroku.api.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.heroku.api.request.vo.MovieDeleteRequestVO;
import com.heroku.api.request.vo.MovieRequestVO;
import com.heroku.api.request.vo.MovieSearchRequestVO;
import com.heroku.api.request.vo.MoviesRequestVO;
import com.heroku.api.request.vo.ReviewSearchRequestVO;
import com.heroku.api.response.vo.CommonResponseEntity;

@Service
public interface MainService {

	public CommonResponseEntity saveMovieRecord(MovieRequestVO movieRequest);
	
	@Transactional
	public CommonResponseEntity deleteMovie(MovieDeleteRequestVO movieDeleteRequest);
	
	public CommonResponseEntity searchMovie(MovieSearchRequestVO movieSearchRequest);
	
	public CommonResponseEntity searchByComments(ReviewSearchRequestVO reviewSearchRequest);
	
	public CommonResponseEntity getMovies(MoviesRequestVO moviesRequest);
}
