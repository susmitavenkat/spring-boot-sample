package com.recommendationengine.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.recommendationengine.demo.dao.RecommendationsRepository;
import com.recommendationengine.demo.model.Movie;

@RestController
public class RecommendationsController {	
	
	@Autowired
	private RecommendationsRepository recommendationsrepository;
	
	@RequestMapping({"/suggestions/{user_id}","/suggestions/","/suggestions"})
	public List<Movie> getRecommendations(@PathVariable Optional<String> user_id) throws IOException {
		String userId="";
		if(user_id.isPresent()) {
			userId=user_id.get();
		}
	return recommendationsrepository.getRecommendations(userId);
	}
}
