package com.ronysky.sevendaysofcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ronysky.sevendaysofcode.entity.Movie;

class HtmlGeneratorTest {

	private HtmlGenerator generator;
	
	
	@Test
	void assertThatACardIsCorrectlyGenerated() {
		generator = new HtmlGenerator(null);
		Movie movie = new Movie("Beauty and the Beast", 1991, "https://m.media-amazon.com/images/M/MV5BMzE5MDM1NDktY2I0OC00YWI5LTk2NzUtYjczNDczOWQxYjM0XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_UX128_CR", "8.0");
		String expected = 
				"""
				<div class="card" style="width: 18rem;">
					<img class="card-img-top" src="https://m.media-amazon.com/images/M/MV5BMzE5MDM1NDktY2I0OC00YWI5LTk2NzUtYjczNDczOWQxYjM0XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_UX128_CR"/>
				</div>
				""";
		assertEquals(expected, generator.generate(movie));
	}

}
