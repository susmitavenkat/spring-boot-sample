package com.recommendationengine.demo.model;

public class Movie {

private String name;
private int released_year;
private String country;
private long likes;
private String genre;


public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getReleased_year() {
	return released_year;
}
public void setReleased_year(int released_year) {
	this.released_year = released_year;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public long getLikes() {
	return likes;
}
public void setLikes(long likes) {
	this.likes = likes;
}
public String getGenre() {
	return genre;
}
public void setGenre(String genre) {
	this.genre = genre;
}
}
