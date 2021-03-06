package com;

import com.exceptions.NoMoreLexemesException;
import com.hashtables.HashTable;

import java.util.ArrayList;
import java.util.List;

public class Translator {

	public static void main(String[] args) {

		long time = System.currentTimeMillis();

		if (args.length > 1) {
			Lexer lexer = new Lexer(Printer.readFile(Printer.getOutputFilePath(args[0])), Printer.getOutputFilePath(args[1]));
			List<Token> tokens = new ArrayList<>();

			try {
				while (true) {
					Token token = lexer.parseNext();
					//Printer.printTokenToConsole(token);
					tokens.add(token);
				}
			} catch (NoMoreLexemesException e) {

				String filePath = Printer.getOutputFilePath(args[1]);

				Printer.createFile(filePath);
				//Printer.printTokensToConsole(tokens);
				System.out.println("Parsing done!");
				System.out.println("Printing results to file");


				HashTable[] tables = lexer.getTables();

				Printer.printTokensToFile(tokens, filePath, tables);

				Printer.printTables(tables, filePath);

			}

			Printer.printLineToFile(Printer.getOutputFilePath(args[1]));

			Printer.printToFile("Time in millis: " + (System.currentTimeMillis() - time), Printer.getOutputFilePath(args[1]));

		} else {
			System.out.println("Use arguments: %name of input file% %name of output file%");
		}



	}


}
