package com.recommendationengine.demo.dao;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recommendationengine.demo.model.Movie;
import com.recommendationengine.demo.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class RecommendationsRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ObjectMapper objectMapper = new ObjectMapper();
    
	public List<Movie> getRecommendations(String userId) throws IOException {
        
        
        List<Movie> movies = objectMapper.readValue(new File("movies.json"), new TypeReference<List<Movie>>(){});
        
        if(userId.isEmpty()) { //guest user
        	        	
        	Collections.sort(movies, new Comparator<Movie>() {
        	    @Override
        	    public int compare(Movie m1, Movie m2) {
        	        if (m1.getLikes() < m2.getLikes())
        	            return 1;
        	        if (m1.getLikes() > m2.getLikes())
        	            return -1;
        	        return 0;
        	    }
        	});
        	return movies.subList(0, 20);
        }else {
        	System.out.println("from else userId"+userId);
        	//get user preferences
        	// filter accordingly
User usr=getUserPreferences(userId);
        	List<Movie> filteredMovies=movies.stream().filter(movie->{        		
        		boolean genrefound =usr.getGenres().contains(movie.getGenre());
        		boolean countryfound =usr.getCountries().contains(movie.getCountry());
        		System.out.println("genrefound "+genrefound);
        		System.out.println("countryfound "+countryfound);
        		
        		if(genrefound && countryfound) {
        			return true;
        		}else {
        			return false;
        		}
        	}).collect(Collectors.toList());
        	
        	//filteredMovies.sort(Comparator.comparing(Movie::getGenre).thenComparing(Movie::getCountry));			        	
        	//movies
        
        	Map<String,List<Movie>> hm =new HashMap<String,List<Movie>>();
        	List<String> genre=usr.getGenres();
        	for ( int i = 0; i < genre.size(); i++) {
        		String value=genre.get(i);
        		List<Movie> fm=filteredMovies.stream().filter(movie ->{            		
            		if(movie.getGenre().equals(value)) {
            			return true;
            		}
					return false;
            	}).collect(Collectors.toList());        		
        		hm.put(genre.get(i), fm);        	        	
			}
        	
        	Iterator it= hm.entrySet().iterator();
        	 List<Movie> fll=new ArrayList<Movie>();
        	while(it.hasNext()) {
                Map.Entry tt = (Map.Entry)it.next();

        		fll.addAll((Collection<? extends Movie>) tt.getValue());
        	}
       
        	return fll;	
        }
        
        //String data= objectMapper.writeValueAsString(movies);
        //logger.info(data);
        
    }
	
	private User getUserPreferences(String userId) throws JsonParseException, JsonMappingException, IOException {

		List<User> users = objectMapper.readValue(new File("users.json"), new TypeReference<List<User>>(){});		
		User userpref = users.stream().filter(user->user.getUserId().equals(userId)).findAny().orElse(null);
		System.out.println("from get user pref"+userpref.toString());
		return userpref;
	}
}
