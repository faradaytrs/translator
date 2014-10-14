package com;

import com.exceptions.NoMoreLexemesException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/*
 * Изотов Андрей ИВТ11-БО
 */

public class Translator {

	public static void main(String[] args) {

		long time = System.currentTimeMillis();

		if (args.length > 1) {
			Lexer lexer = new Lexer(readFile(getOutputFilePath(args[0])), getOutputFilePath(args[1]));
			List<Token> tokens = new ArrayList<Token>();



			try {
				while (true) {
					Token token = lexer.parseNext();
					//Printer.printTokenToConsole(token);
					tokens.add(token);
				}
			} catch (NoMoreLexemesException e) {

				Printer.createFile(getOutputFilePath(args[1]));
				Printer.printTokensToConsole(tokens);
				System.out.println("Parsing done!");
				System.out.println("Printing results to file");
				Printer.printTokensToFile(tokens, getOutputFilePath(args[1]));

			}
		} else {
			System.out.println("Use arguments like: java %filename% %name of input file% %name of output file%");
		}

		Runtime runtime = Runtime.getRuntime();
		long memory = runtime.totalMemory() - runtime.freeMemory();

		System.out.println("Used memory is kilobytes: " + memory/1024);

		System.out.println("Time in millis: " + (System.currentTimeMillis() - time));

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

}
