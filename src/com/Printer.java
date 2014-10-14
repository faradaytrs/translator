package com;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Izotov on 14.10.2014.
 */
public class Printer {

	public static void printTokenToConsole(Token token) {
		List<Token> list = new ArrayList<Token>();
		list.add(token);
		printTokensToConsole(list);
	}

	private static String printText(Token token) {
		return "LEXEME: \'" + token.getToken() + "\'" + " POSITION " + token.getStartingPosition() + " — " + token.getEndingPosition() + " STATE: \'" + token.getState() + "\'";
	}

	public static void printTokenToFile(Token token, String filePath) {
		List<Token> list = new ArrayList<Token>();
		list.add(token);
		printTokensToFile(list, filePath);

	}

	public static void printTokensToConsole(List<Token> list) {
		for (Token token : list) {
			System.out.println(printText(token));
		}
	}

	public static void printTokensToFile(List<Token> list, String filePath) {

		try {
			PrintWriter file = getFile(filePath);
			for (Token token : list) {
				file.println(printText(token));
			}
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		return new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
	}

	public static String readFile(String filename) {
		File f = new File(filename);
		try {
			byte[] bytes = Files.readAllBytes(f.toPath());
			return new String(bytes, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getOutputFilePath(String fileName) {
		String currentWorkingDirectory = System.getProperty("user.dir");
		return currentWorkingDirectory + "\\" + fileName;
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

}
