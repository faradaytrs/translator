package com;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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

	public void printToConsole() {
		System.out.println(printText());
	}

	private String printText() {
		return "LEXEME: \'" + getToken() + "\'" + " POSITION " + getStartingPosition() + " — " + getEndingPosition() + " STATE: \'" + getState() + "\'";
	}

	public void printToFile() {
		//todo rework writing to file, now it works very slow
		try {
			PrintWriter file = getFile("result.txt");
			file.println(printText());
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private PrintWriter getFile(String fileName) throws IOException {
		String path = Translator.getOutputFilePath(fileName);
		return new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
	}

}
