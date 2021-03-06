package com.heroku.api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.heroku.api.model.MovieModel;
import com.heroku.api.model.RatingModel;

@Repository
public interface RatingRepository extends JpaRepository<RatingModel, Integer> {

	List<RatingModel> findByMovieId(MovieModel mm);
	
	@Query(value = "Select r.movie_id from rating r where r.review_comment like %:comments%", nativeQuery = true)
	List<Integer> searchReviewText(@Param("comments") String comments,Pageable pg);
	
}
