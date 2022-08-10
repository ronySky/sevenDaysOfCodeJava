package com.ronysky.sevendaysofcode.parser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ronysky.sevendaysofcode.entity.Movie;

public class ImdbRegexParser implements JsonParser<Movie>{
	
	public List<Movie> getContent(String source){
		return Stream.of(parseToCollection(source))
				.map(movie -> parseMovie(movie))
				.toList();
	}
	
	private Movie parseMovie(String movieJson) {
		return new Movie(
				getAttributeByKey(Keys.title, movieJson),
				Integer.valueOf(getAttributeByKey(Keys.year, movieJson)),
				getAttributeByKey(Keys.image, movieJson),
				getAttributeByKey(Keys.imDbRating, movieJson));		
	}
	
	private String getAttributeByKey(Keys key, String json) {
		String keyString =  String.format("%s\":", key);
		return Stream.of(json.split("\",\""))
			.filter(line -> line.contains(keyString))
			.map(line -> line.split(keyString)[1].substring(1))
			.findFirst().orElse("");		
	}
	
	public static enum Keys {
		title, year, image, imDbRating;
	}
	
	private String[] parseToCollection(String source) {
		Matcher matcher = Pattern.compile(".*\\[(.*)\\].*").matcher(source);
		if (!matcher.matches()) {
			throw new IllegalArgumentException("Não encontrado em " + source);
		}

		String[] colecao = matcher.group(1).split("\\},\\{");

		colecao[0] = colecao[0].substring(1);

		int ultimaPosicao = colecao.length - 1;
		String ultimaString = colecao[ultimaPosicao];
		colecao[ultimaPosicao] = ultimaString.substring(0, ultimaString.length() - 1);
		return colecao;
	}
}
