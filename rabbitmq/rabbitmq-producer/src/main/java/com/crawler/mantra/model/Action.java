package com.crawler.mantra.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Action {

	CREATE("create"), DELETE("delete"), UPDATE("update"), OTHER("other");

	private String name;

	Action(String name) {
		this.name = name;
	}

	public static Action of(String name) {
		return Arrays.stream(Action.values()).filter(action -> action.name.equalsIgnoreCase(name)).findFirst().orElse(OTHER);
	}

}
