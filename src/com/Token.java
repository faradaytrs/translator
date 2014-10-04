package com;

/**
 * Created by farad_000 on 04.10.2014.
 */

public class Token {

	public int getStartingPosition() {
		return startingPosition;
	}

	public int getEndingPosition() {
		return endingPosition;
	}

	public String getToken() {
		return token;
	}

	public int getState() {
		return state;
	}

	private int startingPosition;
	private int endingPosition;
	private String token;
	private int state;

	public Token(int startingPosition, int endingPosition, String token, int state) {
		this.state = state;
		this.token = token;
		this.endingPosition = endingPosition;
		this.startingPosition = startingPosition;
	}


}
