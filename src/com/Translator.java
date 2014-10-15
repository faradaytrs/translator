package com;

import com.exceptions.NoMoreLexemesException;

import java.util.ArrayList;
import java.util.List;/*
 * Изотов Андрей ИВТ11-БО
 */

public class Translator {

	public static void main(String[] args) {

		long time = System.currentTimeMillis();

		if (args.length > 1) {
			Lexer lexer = new Lexer(Printer.readFile(Printer.getOutputFilePath(args[0])), Printer.getOutputFilePath(args[1]));
			List<Token> tokens = new ArrayList<Token>();

			try {
				while (true) {
					Token token = lexer.parseNext();
					//Printer.printTokenToConsole(token);
					tokens.add(token);
				}
			} catch (NoMoreLexemesException e) {

				Printer.createFile(Printer.getOutputFilePath(args[1]));
				//Printer.printTokensToConsole(tokens);
				System.out.println("Parsing done!");
				System.out.println("Printing results to file");
				Printer.printTokensToFile(tokens, Printer.getOutputFilePath(args[1]));

			}

			Printer.printLineToFile(Printer.getOutputFilePath(args[1]));

			Printer.printToFile("Time in millis: " + (System.currentTimeMillis() - time), Printer.getOutputFilePath(args[1]));

		} else {
			System.out.println("Use arguments: %name of input file% %name of output file%");
		}



	}


}
