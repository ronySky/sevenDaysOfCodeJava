package com.ronysky.sevendaysofcode.parser;

import java.util.Collection;

import com.ronysky.sevendaysofcode.entity.Content;

public interface JsonParser<R extends Content> {
	Collection<R> getContent(String json);
}
