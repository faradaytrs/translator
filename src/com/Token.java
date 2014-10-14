package com;

/**
 * Created by farad_000 on 04.10.2014.
 */

public class Token {

	private int startingPosition;
	private int endingPosition;
	private String token;
	private int state;

	public Token(int startingPosition, int endingPosition, String token, int state) {
		setStartingPosition(startingPosition);
		setToken(token);
		setEndingPosition(endingPosition);
		setState(state);
	}

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

	private void setStartingPosition(int startingPosition) {
		this.startingPosition = startingPosition;
	}

	private void setEndingPosition(int endingPosition) {
		this.endingPosition = endingPosition;
	}

	private void setToken(String token) {
		this.token = token;
	}

	private void setState(int state) {
		this.state = state;
	}

}
