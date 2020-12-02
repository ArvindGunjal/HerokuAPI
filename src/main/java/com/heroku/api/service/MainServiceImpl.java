package com.heroku.api.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.heroku.api.model.MovieModel;
import com.heroku.api.model.RatingModel;
import com.heroku.api.repository.MovieRepository;
import com.heroku.api.repository.RatingRepository;
import com.heroku.api.request.vo.MovieDeleteRequestVO;
import com.heroku.api.request.vo.MovieRequestVO;
import com.heroku.api.request.vo.MovieSearchRequestVO;
import com.heroku.api.request.vo.ReviewSearchRequestVO;
import com.heroku.api.response.vo.CommonResponseEntity;
import com.heroku.api.response.vo.MovieSearchResponseVO;
import com.heroku.api.response.vo.RatingSearchResponseVO;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private RatingRepository ratingRepository;

	public CommonResponseEntity saveMovieRecord(MovieRequestVO movieRequest) {

		CommonResponseEntity cre = new CommonResponseEntity();
		DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
		
		try {

			MovieModel movieModelDb = movieRepository.findByName(movieRequest.getMovieName());

			if (movieModelDb != null) {

				cre.setMessage("Duplicate Movie Name");
				cre.setIsException(false);

			} else {

				MovieModel mm = new MovieModel();
				int[] count = {0};
				
				mm.setName(movieRequest.getMovieName());
				mm.setReleaseDate(formatter.parse(movieRequest.getReleaseDate()));

				List<RatingModel> rmList = new ArrayList<RatingModel>();
				movieRequest.getReviews().forEach((ratings) -> {

					RatingModel rm = new RatingModel();
					rm.setMovieId(mm);
					rm.setReviewerName(ratings.getReviewerName());
					rm.setReviewerComments(ratings.getReviewComment());
					rm.setRating(Integer.parseInt(ratings.getRating()) > 10 ? 10 : Integer.parseInt(ratings.getRating()));
					rm.setReviewerComments(ratings.getReviewComment() != null ? ratings.getReviewComment() : null);
					
					count[0] = count[0] + Integer.parseInt(ratings.getRating());
					rmList.add(rm);
				});

				mm.setAvgRating(count[0]/10);
				
				movieRepository.save(mm);
				ratingRepository.saveAll(rmList);

				cre.setData(movieRequest);
				cre.setMessage("Success");
				cre.setIsException(false);

			}
		} catch (Exception ex) {

			cre.setIsException(true);
			cre.setMessage("Something went wrong");
			ex.printStackTrace();

		}

		return cre;
	}

	@Override
	@Transactional
	public CommonResponseEntity deleteMovie(MovieDeleteRequestVO movieDeleteRequest) {
		// TODO Auto-generated method stub

		CommonResponseEntity cre = new CommonResponseEntity();

		try {

			MovieModel movieModelDb = movieRepository.findByName(movieDeleteRequest.getMovieName());
			if(movieModelDb != null) {
				
				movieRepository.deleteByName(movieDeleteRequest.getMovieName());
				cre.setMessage("Movie Remove from Database");
				cre.setIsException(true);
				
				
			}else {
				
				cre.setMessage("Movie Not Available In Database");
				cre.setIsException(false);
				
			}

		} catch (Exception ex) {
				cre.setMessage("Something went wrong");
				cre.setIsException(true);
		}

		return cre;
	}

	@Override
	public CommonResponseEntity searchMovie(MovieSearchRequestVO movieSearchRequest) {
		// TODO Auto-generated method stub
		
		CommonResponseEntity cre = new CommonResponseEntity();
		MovieSearchResponseVO movieSearchResponse = new MovieSearchResponseVO();
		List<RatingSearchResponseVO> ratingSearchList = new ArrayList<RatingSearchResponseVO>();
		
		try {
			
			MovieModel movieModelDb = movieRepository.findByName(movieSearchRequest.getMovieName());
			if(movieModelDb != null) {
				
				Pageable page = PageRequest.of(Integer.parseInt(movieSearchRequest.getPageNo()), Integer.parseInt(movieSearchRequest.getPageSize()));
				List<RatingModel> ratingModel = ratingRepository.findByMovieId(movieModelDb, page);
				movieSearchResponse.setMovieName(movieModelDb.getName());
				movieSearchResponse.setReleaseDate(movieModelDb.getReleaseDate().toString());
				movieSearchResponse.setAvgRating(movieModelDb.getAvgRating() != null ? movieModelDb.getAvgRating().toString() : "NA");
				
				ratingModel.forEach((ratings)->{
					RatingSearchResponseVO ratingSearch = new RatingSearchResponseVO();
					ratingSearch.setRating(ratings.getRating());
					ratingSearch.setReviewerName(ratings.getReviewerName());
					ratingSearch.setReviewerComments(ratings.getReviewerComments());
					ratingSearchList.add(ratingSearch);
				});
				
				
				movieSearchResponse.setReviews(ratingSearchList);
				
				cre.setData(movieSearchResponse);
				cre.setMessage("Movie List Generated Successfully");
				cre.setIsException(false);
				
			}else {
				
				cre.setMessage("Movie Not Available In Database");
				cre.setIsException(false);
				
			}
			
		}catch(Exception ex) {
			cre.setMessage("Something went wrong");
			cre.setIsException(true);
		}
		
		return cre;
	}

	@Override
	public CommonResponseEntity searchByComments(ReviewSearchRequestVO reviewSearchRequest) {
		// TODO Auto-generated method stub
		
		CommonResponseEntity cre = new CommonResponseEntity();
		
		try {
			
			Pageable page = PageRequest.of(Integer.parseInt(reviewSearchRequest.getPageNo()), Integer.parseInt(reviewSearchRequest.getPageSize()));
			List<MovieModel> ratingSearchList = new ArrayList<MovieModel>();
			
			List<Integer> ratingList = ratingRepository.searchReviewText(reviewSearchRequest.getReview(),page);
			
			if(!ratingList.isEmpty()) {
				
//				reviewSearchResponse.setReviewModel(ratingList);
				List<Integer> uniqueList = ratingList.stream().distinct().collect(Collectors.toList());
				
				uniqueList.forEach((movieId)->{
					ratingSearchList.add(movieRepository.findByMovieId(movieId));
				});
				
				cre.setMessage("Result Found");
				cre.setData(ratingSearchList);
				cre.setIsException(false);	
				
			}else {
				cre.setMessage("No Result Found");
				cre.setIsException(false);	
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			cre.setMessage("Something went wrong");
			cre.setIsException(true);
		}

		return cre;
	}
	
}
