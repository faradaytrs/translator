package com;

/**
 * Created by farad_000 on 04.10.2014.
 */

public class Token {

	private int startingPosition;
	private int endingPosition;
	//private String token;
	private int state;

	public int getIndex() {
		return index;
	}

	private void setIndex(int index) {
		this.index = index;
	}

	public int getNumberOfTable() {
		return numberOfTable;
	}

	private void setNumberOfTable(int numberOfTable) {
		this.numberOfTable = numberOfTable;
	}

	private int index;
	private int numberOfTable;

	public Token(int startingPosition, int endingPosition, int index, int numberOfTable, int state) {
		setStartingPosition(startingPosition);
		//setToken(token);
		setIndex(index);
		setNumberOfTable(numberOfTable);
		setEndingPosition(endingPosition);
		setState(state);
	}

	public int getStartingPosition() {
		return startingPosition;
	}

	public int getEndingPosition() {
		return endingPosition;
	}

//	public String getToken() {
//		return token;
//	}

	public int getState() {
		return state;
	}

	private void setStartingPosition(int startingPosition) {
		this.startingPosition = startingPosition;
	}

	private void setEndingPosition(int endingPosition) {
		this.endingPosition = endingPosition;
	}

//	private void setToken(String token) {
//		this.token = token;
//	}

	private void setState(int state) {
		this.state = state;
	}

}
