package com.ronysky.sevendaysofcode.parser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ronysky.sevendaysofcode.entity.Movie;

public class ImdbGsonParser {

	private String source;
	
	public ImdbGsonParser(String source) {
		this.source = source;
	}
	
	public void a() {
		System.out.println("Desserializing");
		Movie movie = new Gson().fromJson(source, Movie.class);
		System.out.println(movie.title());
		Type targetClassType = new TypeToken<ArrayList<Movie>>() {}.getType();
		Collection<Movie> movies = new Gson().fromJson(source, targetClassType); 
		System.out.println(movies.size());
	}
}
