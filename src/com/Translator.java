package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Изотов Андрей ИВТ11-БО
 */

public class Translator {

	public static void main(String[] args) {

		String currentWorkingDirectory = System.getProperty("user.dir");

		if (args.length > 0) {
			Lexer lexer = new Lexer(readFile(currentWorkingDirectory + "\\src\\com\\" + args[0]));
		} else {
			System.out.println("Set filename as argument");
		}

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
