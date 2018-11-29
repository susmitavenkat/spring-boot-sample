package com.recommendationengine.demo.model;

import java.util.List;

public class User {
		private String userId;
		private String name;
		private String email;
		private List<String> genres;
		private List<String> countries;
		private List<String> watchHistory;
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public List<String> getGenres() {
			return genres;
		}
		public void setGenres(List<String> genres) {
			this.genres = genres;
		}
		public List<String> getCountries() {
			return countries;
		}
		public void setCountries(List<String> countries) {
			this.countries = countries;
		}
		public List<String> getWatchHistory() {
			return watchHistory;
		}
		public void setWatchHistory(List<String> watchHistory) {
			this.watchHistory = watchHistory;
		}
		
		
		
}



