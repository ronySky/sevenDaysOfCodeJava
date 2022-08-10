package com.ronysky.sevendaysofcode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.ronysky.sevendaysofcode.entity.Movie;
import com.ronysky.sevendaysofcode.parser.ImdbStringParser;
import com.ronysky.sevendaysofcode.parser.ImdbStringParser.Keys;

class StringParserTest {

	private ImdbStringParser parser = new ImdbStringParser("{\"items\":[{\"id\":\"tt0111161\",\"rank\":\"1\",\"title\":\"The Shawshank Redemption\",\"fullTitle\":\"The Shawshank Redemption (1994)\",\"year\":\"1994\",\"image\":\"https://imdb-api.com/images/original/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_Ratio0.6716_AL_.jpg\",\"crew\":\"Frank Darabont (dir.), Tim Robbins, Morgan Freeman\",\"imDbRating\":\"9.2\",\"imDbRatingCount\":\"2612945\"},{\"id\":\"tt0068646\",\"rank\":\"2\",\"title\":\"The Godfather\",\"fullTitle\":\"The Godfather (1972)\",\"year\":\"1972\",\"image\":\"https://imdb-api.com/images/original/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_Ratio0.7015_AL_.jpg\",\"crew\":\"Francis Ford Coppola (dir.), Marlon Brando, Al Pacino\",\"imDbRating\":\"9.2\",\"imDbRatingCount\":\"1808399\"},{\"id\":\"tt0468569\",\"rank\":\"3\",\"title\":\"The Dark Knight\",\"fullTitle\":\"The Dark Knight (2008)\",\"year\":\"2008\",\"image\":\"https://imdb-api.com/images/original/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_Ratio0.6716_AL_.jpg\",\"crew\":\"Christopher Nolan (dir.), Christian Bale, Heath Ledger\",\"imDbRating\":\"9.0\",\"imDbRatingCount\":\"2585014\"},{\"id\":\"tt0071562\",\"rank\":\"4\",\"title\":\"The Godfather Part II\",\"fullTitle\":\"The Godfather Part II (1974)\",\"year\":\"1974\",\"image\":\"https://imdb-api.com/images/original/MV5BMWMwMGQzZTItY2JlNC00OWZiLWIyMDctNDk2ZDQ2YjRjMWQ0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_Ratio0.7015_AL_.jpg\",\"crew\":\"Francis Ford Coppola (dir.), Al Pacino, Robert De Niro\",\"imDbRating\":\"9.0\",\"imDbRatingCount\":\"1243583\"}],\"errorMessage\":\"\"}");
	
	@Test
	void assertThatTitlesAreProperlyListed() {
		List<String> titles = Arrays.asList("The Shawshank Redemption", "The Godfather", "The Dark Knight", "The Godfather Part II");
		assertEquals(titles, parser.listValuesByKey(Keys.title));
	}
	
	@Test
	void assertThatTitlesWithCommasAreProperlyParsed() {
		parser.setSource("{\"items\":[{\"id\":\"tt0060196\",\"rank\":\"10\",\"title\":\"The Good, the Bad and the Ugly\",\"fullTitle\": \"The Good, the Bad and the Ugly (1966)\",\"year\":\"1966\",\"image\":\"https://m.media-amazon.com/images/M/MV5BNjJlYmNkZGItM2NhYy00MjlmLTk5NmQtNjg1NmM2ODU4OTMwXkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_UX128_CR0,3,128,176_AL_.jpg\",\"crew\": \"Sergio Leone (dir.), Clint Eastwood, Eli Wallach\",\"imDbRating\": \"8.8\",\"imDbRatingCount\": \"748639\"}]\"errorMessage\":\"\"}");
		assertEquals("The Good, the Bad and the Ugly",parser.parseToCollection().get(0).title());
	}
	
	@Test
	void assertThatYearsAreProperlyListed() {
		List<String> years = Arrays.asList("1994", "1972", "2008", "1974");
		assertEquals(years, parser.listValuesByKey(Keys.year));
	}
	
	@Test
	void assertThatObjectsAreProperlyParsed() {
		Movie movie = new Movie("The Shawshank Redemption", 1994, "https://imdb-api.com/images/original/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_Ratio0.6716_AL_.jpg", "9.2");
		assertEquals(movie, parser.parseToCollection().get(0));
	}

}
