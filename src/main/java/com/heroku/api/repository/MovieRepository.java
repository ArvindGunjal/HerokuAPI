package com.heroku.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heroku.api.model.MovieModel;

@Repository
public interface MovieRepository extends JpaRepository<MovieModel, Integer> {

	MovieModel findByName(String movieName);
	
	void deleteByName(String movieName);

	MovieModel findByMovieId(Integer movieId);
	
}
