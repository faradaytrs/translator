package com;

import com.hashtables.HashTable;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Izotov on 14.10.2014.
 */
public class Printer {

	private static String printText(Token token, HashTable[] tables) {
		int tableNumber = token.getNumberOfTable();
		HashTable table = tables[tableNumber];
		int index = token.getIndex();
		String lexeme = table.getByIndex(index);
		return lexeme + ": " + getNameOfTableByState(token.getState()) + " (" + token.getState() + ")" + " INDEX " + token.getIndex();
	}

	public static void printTokenToFile(Token token, String filePath, HashTable[] tables) {
		List<Token> list = new ArrayList<>();
		list.add(token);
		printTokensToFile(list, filePath, tables);

	}

	public static void printTokensToFile(List<Token> list, String filePath, HashTable[] tables) {

		try {
			PrintWriter file = getFile(filePath);
			for (Token token : list) {
				file.println(printText(token, tables));
			}
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void printTables(HashTable[] tables, String filePath) {

		//print int table
		Printer.printLineToFile(filePath);
		Printer.printToFile("INT TABLE" ,filePath);
		Printer.printLineToFile(filePath);
		Printer.printHashTableToFile(tables[0], filePath);

		//id table
		Printer.printLineToFile(filePath);
		Printer.printToFile("ID TABLE" ,filePath);
		Printer.printLineToFile(filePath);
		Printer.printHashTableToFile(tables[1], filePath);

		//keywords
		Printer.printLineToFile(filePath);
		Printer.printToFile("KEYWORD TABLE" ,filePath);
		Printer.printLineToFile(filePath);
		Printer.printHashTableToFile(tables[2], filePath);

		//float table
		Printer.printLineToFile(filePath);
		Printer.printToFile("FLOAT TABLE" ,filePath);
		Printer.printLineToFile(filePath);
		Printer.printHashTableToFile(tables[3], filePath);

		//double float table
		Printer.printLineToFile(filePath);
		Printer.printToFile("DOUBLE FLOAT TABLE" ,filePath);
		Printer.printLineToFile(filePath);
		Printer.printHashTableToFile(tables[4], filePath);

	}

	public static void createFile(String outputFilePath) {
		try {
			//rewriting old file
			new PrintWriter(outputFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static PrintWriter getFile(String filePath) throws IOException {
		return new PrintWriter(new FileWriter(filePath, true));
	}

	public static String readFile(String filename) {
		File f = new File(filename);
		try {
			byte[] bytes = Files.readAllBytes(f.toPath());
			return new String(bytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getOutputFilePath(String fileName) {
		String currentWorkingDirectory = System.getProperty("user.dir");
		return currentWorkingDirectory + File.separator + fileName;
	}

	private static String getNameOfTableByState(int state) {
		switch (state) {

			case Lexer.INT:
				return "INT";
			case Lexer.ID:
				return "ID";
			case Lexer.FLOAT_KEYWORD | Lexer.DOUBLE_KEYWORD:
			case Lexer.DOUBLE_KEYWORD:
			case Lexer.RETURN_KEYWORD:
				return "KEYWORD";
			case -1:
			case -2:
			case -3:
			case -4:
				return "FLOAT";
			case -5:
				return "DOUBLE FLOAT";
			case Lexer.SPACE:
				return "SPACE";
			case Lexer.CLOSING_CURLY_BRACE:
			case Lexer.OPENING_CURLY_BRACE:
			case Lexer.OPENING_ROUND_BRACE:
			case Lexer.CLOSING_ROUND_BRACE:
			case Lexer.SEMICOLON:
				return "SIGN";
			case Lexer.ERROR:
				return "ERROR";

		}
		return "";
	}

	public static void printToFile(String info, String filePath) {
		try {
			PrintWriter file = getFile(filePath);
			file.println(info);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void printLineToFile(String filepath) {
		printToFile("==================================================", filepath);
	}

	public static void printHashTableToFile(HashTable table, String filePath) {

		try {
			PrintWriter file = getFile(filePath);
			int length = table.getLengthOfArray();

			for (int i = 0; i < length; i++) {
				String str = table.getByIndex(i);
				if (str != null) {
					file.println("INDEX " + i + " STRING: " + str);
				}
			}
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
