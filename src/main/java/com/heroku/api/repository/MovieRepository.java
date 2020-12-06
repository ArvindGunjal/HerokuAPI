package com.heroku.api.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.heroku.api.model.MovieModel;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<MovieModel, Integer> {

	MovieModel findByNameIgnoreCase(String movieName);
	
	void deleteByName(String movieName);

	MovieModel findByMovieId(Integer movieId);
	
	Page<MovieModel> findAll(Pageable pg);
	
}
