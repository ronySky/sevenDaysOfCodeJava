package com.ronysky.sevendaysofcode.client;

import java.net.URI;
import java.net.URISyntaxException;

public class ImdbAPIClient extends BaseAPIClient implements APIClient {
	
	private static final String ENDPOINT = "https://imdb-api.com/en/API/Top250Movies/";
	
	public URI buildURI() throws URISyntaxException {
		return new URI(String.format("%s%s", ENDPOINT, getApiKey()));
	}
	
	private String getApiKey() {
		String apiKey = System.getenv("IMDB_API_KEY");
		if (apiKey == null) {
			apiKey = System.getProperty("ImdbApiKey");
		}
		return apiKey;
	}
}