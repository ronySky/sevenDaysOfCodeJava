package com.ronysky.sevendaysofcode.entity;

public interface Content extends Comparable<Content> {
	String title();
	Integer year();
	String imageUrl();
	String rating();
}
