package com.ronysky.sevendaysofcode.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.ronysky.sevendaysofcode.HtmlGenerator;
import com.ronysky.sevendaysofcode.client.APIClient;
import com.ronysky.sevendaysofcode.client.ImdbAPIClient;
import com.ronysky.sevendaysofcode.client.MarvelAPIClient;
import com.ronysky.sevendaysofcode.entity.Content;
import com.ronysky.sevendaysofcode.entity.Movie;
import com.ronysky.sevendaysofcode.entity.Serie;
import com.ronysky.sevendaysofcode.parser.ImdbRegexParser;
import com.ronysky.sevendaysofcode.parser.JsonParser;
import com.ronysky.sevendaysofcode.parser.MarvelRegexParser;

@Component
public class ContentService {

	@PostConstruct
	public void getContent() {
		Collection<Movie> movies = getMovies();
		Collection<Serie> series = getSerie();
		
		
		List<? extends Content> mixedList = Stream.of(movies, series)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
		
		//Collections.sort(mixedList, Comparator.comparing(Content::year));

		try {
			HtmlGenerator generator = new HtmlGenerator(new PrintWriter("/home/ronysky/Desktop/content.html"));
			generator.generate(mixedList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public Collection<Movie> getMovies() {
		try {
			APIClient client = new ImdbAPIClient();
			JsonParser<Movie> parser = new ImdbRegexParser();
			return parser.getContent(client.body());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	private Collection<Serie> getSerie() {
		try {
			APIClient client = new MarvelAPIClient();
			JsonParser<Serie> parser = new MarvelRegexParser();
			return parser.getContent(client.body());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
		
	}
}
