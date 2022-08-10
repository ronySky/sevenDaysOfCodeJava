package com.ronysky.sevendaysofcode.entity;

public record Movie(String title, Integer year, String imageUrl, String rating) implements Content{

	public int compareTo(Content o) {
		return this.year().compareTo(o.year());
	}
}
