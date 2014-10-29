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
			List<Token> tokens = new ArrayList<Token>();

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
				Printer.printTokensToFile(tokens, filePath);

				HashTable[] tables = lexer.getTables();

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

//				for (HashTable table : tables) {
//					Printer.printLineToFile(filePath);
//					Printer.printHashTableToFile(table, filePath);
//				}

			}

			Printer.printLineToFile(Printer.getOutputFilePath(args[1]));

			Printer.printToFile("Time in millis: " + (System.currentTimeMillis() - time), Printer.getOutputFilePath(args[1]));

		} else {
			System.out.println("Use arguments: %name of input file% %name of output file%");
		}



	}


}
