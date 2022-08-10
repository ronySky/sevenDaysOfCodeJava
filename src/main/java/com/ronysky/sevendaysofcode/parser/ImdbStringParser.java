package com.ronysky.sevendaysofcode.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.ronysky.sevendaysofcode.entity.Movie;

public class ImdbStringParser {

	private String source;
	private List<Movie> movies;
	
	public ImdbStringParser(String source) {
		this.source = source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}

	
	public List<String> listValuesByKey(Keys key) {
		return listValuesByKey(key, source);
	}
	
	public List<String> listValuesByKey(Keys key, String source) {
		String[] lines = source.split(",");
		String tag = String.format("\"%s\":", key);
		return Stream.of(lines)
				.filter(line -> line.contains(tag)) //Only get strings with the key
				.map(line -> line.substring(line.indexOf(":") + 2 , line.length()-1)) //Gets the string after the first index of ":". This solution breaks if a key uses a colon 
				.toList();
	}
	
	public String getValueByKey(Keys key, String source) {
		String[] lines = source.split(",\"");
		String tag = String.format("\"%s\":", key);
		return Stream.of(lines)
				.filter(line -> line.contains(tag))
				.peek(System.out::println)
				.map(line -> line.substring(line.indexOf(":") + 2, line.length()-1))
				.findFirst().get();
	}

	public static enum Keys {
		title, year, image, imDbRating;
	}
	
	public List<Movie> parseToCollection(){
		return parseToCollection(source);
	}
	
	public List<Movie> parseToCollection(String source){
		if (movies == null) {
			movies = new ArrayList<Movie>();
			String[] jsonMovies = source.split("\\}\\,\\{");
			for (String jsonMovie : jsonMovies) {
				String title = getValueByKey(Keys.title, jsonMovie);
				int year = Integer.valueOf(getValueByKey(Keys.year, jsonMovie));
				String image = getValueByKey(Keys.image, jsonMovie);
				String rating = getValueByKey(Keys.imDbRating, jsonMovie);
				movies.add(new Movie(title, year, image, rating));
			}
		}
		return movies;
	}
}
