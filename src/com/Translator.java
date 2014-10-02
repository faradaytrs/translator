package com;

import java.io.*;
import java.nio.file.Files;

/**
 * Изотов Андрей ИВТ11-БО
 */
public class Translator {

	public static void main(String[] args) {

		Lexer lexer = new Lexer(readFile("C:\\Users\\farad_000\\IdeaProjects\\translator\\src\\com\\code.txt"));

	}

	public static String readFile(String filename) {
		File f = new File(filename);
		try {
			byte[] bytes = Files.readAllBytes(f.toPath());
			return new String(bytes,"UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

}
