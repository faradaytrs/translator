package tests;

import com.Lexer;
import com.Printer;
import com.Token;
import com.exceptions.NoMoreLexemesException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Andrey Izotov on 13.10.2014.
 */

public class TestLexer {

	private Lexer lexer;

	@Before
	public void setUp() {
		lexer = new Lexer("public static void main(String[] args) { # return 5.5 + 10; }", Printer.getOutputFilePath("result.txt"));
	}

	@Test
	public void testParseNextId() {
		lexer = new Lexer("id", Printer.getOutputFilePath("result.txt"));
		try {
			Token token = lexer.parseNext();
			assertEquals(Lexer.ID, token.getState());
		} catch (NoMoreLexemesException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testParseNextKeyWord() {
		lexer = new Lexer("return", Printer.getOutputFilePath("result.txt"));
		try {
			Token token = lexer.parseNext();
			assertEquals(Lexer.RETURN_KEYWORD, token.getState());
		} catch (NoMoreLexemesException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testParseNextFloat() {
		lexer = new Lexer("5.5", Printer.getOutputFilePath("result.txt"));
		try {
			Token token = lexer.parseNext();
			assertEquals(-2, token.getState());
		} catch (NoMoreLexemesException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testParseNextInt() {
		lexer = new Lexer("56785", Printer.getOutputFilePath("result.txt"));
		try {
			Token token = lexer.parseNext();
			assertEquals(Lexer.INT, token.getState());
		} catch (NoMoreLexemesException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testParseNextDoubleFloat() {
		lexer = new Lexer("0.657d-6", Printer.getOutputFilePath("result.txt"));
		try {
			Token token = lexer.parseNext();
			assertEquals(-5, token.getState());
		} catch (NoMoreLexemesException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testParseNextSpace() {
		lexer = new Lexer("                                     ", Printer.getOutputFilePath("result.txt"));
		try {
			Token token = lexer.parseNext();
			assertEquals(Lexer.SPACE, token.getState());
		} catch (NoMoreLexemesException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testParseNextSemiColon() {
		lexer = new Lexer(";", Printer.getOutputFilePath("result.txt"));
		try {
			Token token = lexer.parseNext();
			assertEquals(Lexer.SEMICOLON, token.getState());
		} catch (NoMoreLexemesException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testParseNextOpeningCurlyBrace() {
		lexer = new Lexer("{", Printer.getOutputFilePath("result.txt"));
		try {
			Token token = lexer.parseNext();
			assertEquals(Lexer.OPENING_CURLY_BRACE, token.getState());
		} catch (NoMoreLexemesException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testParseNextClosingCurlyBrace() {
		lexer = new Lexer("}", Printer.getOutputFilePath("result.txt"));
		try {
			Token token = lexer.parseNext();
			assertEquals(Lexer.CLOSING_CURLY_BRACE, token.getState());
		} catch (NoMoreLexemesException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testParseNextOpeningRoundBrace() {
		lexer = new Lexer("(", Printer.getOutputFilePath("result.txt"));
		try {
			Token token = lexer.parseNext();
			assertEquals(Lexer.OPENING_ROUND_BRACE, token.getState());
		} catch (NoMoreLexemesException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testParseNextClosingRoundBrace() {
		lexer = new Lexer(")", Printer.getOutputFilePath("result.txt"));
		try {
			Token token = lexer.parseNext();
			assertEquals(Lexer.CLOSING_ROUND_BRACE, token.getState());
		} catch (NoMoreLexemesException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testParseNextError() {
		lexer = new Lexer(".......................", Printer.getOutputFilePath("result.txt"));
		try {
			Token token = lexer.parseNext();
			assertEquals(Lexer.ERROR, token.getState());
		} catch (NoMoreLexemesException e) {
			e.printStackTrace();
		}
	}

}
