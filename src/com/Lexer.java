package com;

/**
 * Изотов Андрей ИВТ11-БО
 */

public class Lexer {

	public static final int LENGTH = 7;

	public static final int SPACE = -13;
	public static final int FLOAT_KEYWORD = -101;
	public static final int DOUBLE_KEYWORD = -102;
	public static final int RETURN_KEYWORD = -103;
	public static final int OPENING_CURLY_BRACE = -14;
	public static final int CLOSING_CURLY_BRACE = -15;
	public static final int OPENING_ROUND_BRACE = -16;
	public static final int CLOSING_ROUND_BRACE = -17;
	public static final int SEMICOLON = -18;

	public Lexer(String code) {
		parse(code);
	}

	private void parse(String code) {

		int[] states;
		int length = code.length();

		states = initStates();

		int lastFinalState = 0;
		int lastPositionWithFinalState = 0;
		int startingPosition = 0;

		for (int i=0; i < length; i++) {

			identify(code.charAt(i), states);

			if (areAllBroken(states)) {
				if (lastFinalState == 0) {
					//std::cout << "POSITION: " << i << " ERROR CANNOT RECOGNIZE SYMBOL: " << text[i] << " CODE " << (int) text[i] << std::endl;
					System.out.println("POSITION: " + i + " ERROR CANNOT RECOGNIZE SYMBOL: " + code.charAt(i) + " CODE " + (int)code.charAt(i));
					states = initStates();
				} else {
					//success //todo add to hash table
					//std::cout << "POSITION: " << startingPosition+1 << " - " << lastPositionWithFinalState << " FINAL STATE: " << lastFinalState << std::endl;
					System.out.println("POSITION: " + (startingPosition + 1) + " — " + lastPositionWithFinalState + " FINAL STATE: " + lastFinalState);
					lastFinalState = 0;
					i = lastPositionWithFinalState;
					startingPosition = i;
					states = initStates();
				}
			} else {
				if (getFinalState(states) != 0) {
					lastFinalState = getFinalState(states);
					lastPositionWithFinalState = i;
				}
			}
		}

	}

	private boolean areAllBroken(int[] states) {
		//temporary code
		for (int i : states) {
			if (i != 0) return false;
		}
		return true;
	}

	private int getFinalState(int[] states) {

		//ORDER MATTERS
		//=============

		//keyword
		if (states[2] < 0) {
			return states[2];
		}

		//int
		if (states[0] < 0) {
			return states[0];
		}

		//identifier
		if (states[1] < 0) {
			return states[1];
		}

		//float
		if (states[3] < 0) {
			return states[3];
		}

		//double float
		if (states[4] < 0) {
			return states[4];
		}

		//space
		if (states[5] < 0) {
			return states[5];
		}

		//sign
		if (states[6] < 0) {
			return states[6];
		}

		return 0;
	}

	private void identify(char c, int[] states) {

		states[0] = identifyInt(c, states[0]);
		states[1] = identifyIdentifier(c, states[1]);
		states[2] = identifyKeyWord(c, states[2]);
		states[3] = identifyFloat(c, states[3]);
		states[4] = identifyDoubleFloat(c, states[4]);
		states[5] = identifySpace(c, states[5]);
		states[6] = identifySign(c, states[6]);

	}

	private int[] initStates() {
		int[] arr = new int[LENGTH];
		for (int i=0; i< LENGTH; i++) {
			arr[i] = 1;
		}
		return arr;
	}

	private int identifyInt(char ch, int state) {
		switch (state) {
			case 1:
				if (Character.isLetter(ch)) {
					return -18;
				}
				return 0;
			case -18:
				if (Character.isDigit(ch)) {
					return -18;
				}
				return 0;
		}
		return 0;
	}

	private int identifyIdentifier(char ch, int state) {
		switch (state) {
			case 1:
				if (Character.isLetter(ch)) {
					return -6;
				}
			case -6:
				if (Character.isLetter(ch)) {
					return -6;
				}
				//-7 - -12 reserved by this FSM
		}
		return 0;
	}

	//todo fill keywords
	private int identifyKeyWord(char ch, int state) {
		switch (state) {
			case 1:
				if (ch == 'f') {
					return 2;
				}
				if (ch == 'd') {
					return 6;
				}
				if (ch == 'r') {
					return 11;
				}
				return 0;
			case 2:
				if (ch == 'l') {
					return 3;
				}
				return 0;
			case 3:
				if (ch == 'o') {
					return 4;
				}
				return 0;
			case 4:
				if (ch == 'a') {
					return 5;
				}
				return 0;
			case 5:
				if (ch == 't') {
					return FLOAT_KEYWORD;
				}
				return 0;
			case 6:
				if (ch == 'o') {
					return 7;
				}
				return 0;
			case 7:
				if (ch == 'u') {
					return 8;
				}
				return 0;
			case 8:
				if (ch == 'b') {
					return 9;
				}
				return 0;
			case 9:
				if (ch == 'l') {
					return 10;
				}
				return 0;
			case 10:
				if (ch == 'e') {
					return DOUBLE_KEYWORD;
				}
				return 0;
			case 11:
				if (ch == 'e') {
					return 12;
				}
				return 0;
			case 12:
				if (ch == 't') {
					return 13;
				}
				return 0;
			case 13:
				if (ch == 'u') {
					return 14;
				}
				return 0;
			case 14:
				if (ch == 'r') {
					return 15;
				}
				return 0;
			case 15:
				if (ch == 'n') {
					return RETURN_KEYWORD;
				}
				return 0;
		}
		return 0;
	}

	private int identifyFloat(char ch, int state) {
		switch (state) {
			case 1:
				if (ch == '0') {
					return 4;
				}
				if (Character.isDigit(ch)) {
					return 2;
				}
				if (ch == '.') {
					return 3;
				}
				return 0;
			case 2:
				if (Character.isDigit(ch)) {
					return 2;
				}
				if (ch == '.') return -1;
				return 0;
			case -1:
				if (Character.isDigit(ch)) {
					return -2;
				}
				return 0;
			case -2:
				if (Character.isDigit(ch)) {
					return -2;
				}
				return 0;
			case 3:
				if (Character.isDigit(ch)) {
					return -3;
				}
				return 0;
			case -3:
				if (Character.isDigit(ch)) {
					return -3;
				}
				return 0;
			case 4:
				if (ch == '.') {
					return 5;
				}
				return 0;
			case 5:
				if (Character.isDigit(ch)) {
					return 6;
				}
				return 0;
			case 6:
				if (Character.isDigit(ch)) {
					return 6;
				}
				if (ch == 'e') {
					return 7;
				}
				return 0;
			case 7:
				if (ch == '+' || ch == '-') {
					return 8;
				}
				return 0;
			case 8:
				if (Character.isDigit(ch)) {
					return -4;
				}
				return 0;
			case -4:
				if (Character.isDigit(ch)) {
					return -4;
				}
				return 0;
		}
		return 0;
	}

	private int identifyDoubleFloat(char ch, int state) {
		switch (state) {
			case 1:
				if (ch == '0') {
					return 2;
				}
				return 0;
			case 2:
				if (ch == '.') {
					return 3;
				}
				return 0;
			case 3:
				if (Character.isDigit(ch)) {
					return 4;
				}
				return 0;
			case 4:
				if (Character.isDigit(ch)) {
					return 4;
				}
				if (ch == 'd') {
					return 5;
				}
				return 0;
			case 5:
				if (ch == '+' || ch == '-') {
					return 6;
				}
				return 0;
			case 6:
				if (Character.isDigit(ch)) {
					return -5;
				}
				return 0;
			case -5:
				if (Character.isDigit(ch)) {
					return -5;
				}
				return 0;
		}
		return 0;
	}

	private int identifySpace(char ch, int state) {
		switch (state) {
			case 1:
				if (ch == ' ') {
					return SPACE;
				}
				if (ch == ' ') {
					return SPACE;
				}
				if (ch == '\n') {
					return SPACE;
				}
				return 0;
			case SPACE:
				if (ch == ' ') {
					return SPACE;
				}
				if (ch == ' ') {
					return SPACE;
				}
				if (ch == '\n') {
					return SPACE;
				}
				return 0;
		}
		return 0;
	}

	private int identifySign(char ch, int state) {
		switch (state) {
			case 1:
				if (ch == ';') {
					return SEMICOLON;
				}
				if (ch == '{') {
					return OPENING_CURLY_BRACE;
				}
				if (ch == '}') {
					return CLOSING_CURLY_BRACE;
				}
				if (ch == '(') {
					return OPENING_ROUND_BRACE;
				}
				if (ch == ')') {
					return CLOSING_ROUND_BRACE;
				}
				return 0;
		}
		return 0;
	}

}
