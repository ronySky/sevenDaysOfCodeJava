package com.ronysky.sevendaysofcode;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

import com.ronysky.sevendaysofcode.entity.Content;

public class HtmlGenerator {

	public Writer writer;
	
	private final String MAIN_TEMPLATE = 
			"""
			<html>
				<head>
						<meta charset="utf-8">
						<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
						<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" 
						integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
				</head>
				<body>
						<div id="flexContainer" style="display: flex; flex-wrap:wrap;">
								%s
						</div>
				</body>
			</html>
			""";
	
	private final String CARD_TEMPLATE =
			"""
			<div class="card" style="width: 18rem;">
				<div class="card-body">
					<h5 class="card-title">%s (%d)</h5>
					<img class="card-img-top" src="%s"/>
					<p class="card-text">Rating: %s</p>
				</div>
			</div>
			""";
	
	public HtmlGenerator(Writer writer) {
		this.writer = writer;
	}
	
	public void generate(Collection<? extends Content> content) throws IOException {
		StringBuilder sb = new StringBuilder();
		content.forEach(record -> sb.append(generate(record)));
		try {
				writer.write(String.format(MAIN_TEMPLATE, sb.toString()));
		}
		finally{
			writer.close();
		}
	}
	
	public String generate(Content content) {
		return String.format(CARD_TEMPLATE, content.title(), content.year(), content.imageUrl(), content.rating());
	}
}
