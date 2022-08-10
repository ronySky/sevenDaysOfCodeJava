package com.ronysky.sevendaysofcode.client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public abstract class BaseAPIClient implements APIClient {

	@Override
	public String body() {
		System.out.println("Calling service");
		HttpClient client = HttpClient.newHttpClient();
				
		try { 
			HttpRequest request = HttpRequest.newBuilder(buildURI()).GET().build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			
			int responseCode = response.statusCode();
			System.out.println(responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) {
				return response.body();
			}
			else {
				throw new RuntimeException("Erro HTTP ao chamar API: " + response.statusCode());
			}
		} catch (IOException | InterruptedException | URISyntaxException e) {
			throw new RuntimeException("Erro na chamada da API", e);
		}
	}
	
	public abstract URI buildURI() throws URISyntaxException;
}