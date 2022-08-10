package com.ronysky.sevendaysofcode.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;

import org.apache.commons.codec.digest.DigestUtils;

public class MarvelAPIClient extends BaseAPIClient implements APIClient{
	
	private static final String ENDPOINT = "https://gateway.marvel.com/v1/public/series?ts=%s&apikey=%s&hash=%s";
	//private static final String ENDPOINT = "https://gateway.marvel.com/v1/public/comics/82967?ts=%s&apikey=%s&hash=%s";
	

	public URI buildURI() throws URISyntaxException {
		String now = String.valueOf(Instant.now().getEpochSecond());
		return new URI(String.format(ENDPOINT, now, getPublicKey(), generateHash(now)));
	}
	
	private String getPublicKey() {
		String apiKey = System.getenv("MARVEL_PUBLIC_API_KEY");
		if (apiKey == null) {
			apiKey = System.getProperty("MarvelPublicApiKey");
		}
		return apiKey;
	}
	
	private String getPrivateKey() {
		String apiKey = System.getenv("MARVEL_PRIVATE_API_KEY");
		if (apiKey == null) {
			apiKey = System.getProperty("MarvelPrivateApiKey");
		}
		return apiKey;
	}
	
	private String generateHash(String timestamp) {
		return DigestUtils.md5Hex(timestamp + getPrivateKey() + getPublicKey());
	}	
}