package com;

import exceptions.NoPlaceInTableException;
import exceptions.NoSuchElementException;

/**
 * Created by farad_000 on 03.10.2014.
 */
public class HashTable {

	private final String[] table;

	int lengthOfArray;

	public HashTable(int lengthOfArray) {
		setLengthOfArray(lengthOfArray);
		table = new String[lengthOfArray];
	}

	public int getLengthOfArray() {
		return lengthOfArray;
	}

	private void setLengthOfArray(int lengthOfArray) {
		this.lengthOfArray = lengthOfArray;
	}

	private int getHash(String str, int step) {
		int length = str.length();
		int index = 0;
		for (int i = 0; i < length; i++) {
			index += (int)str.charAt(i);
		}
		index += step;

		//here we multiply our number with prime number to make less collisions for small identifiers
		index *= 7;
		index %= lengthOfArray;

		return index;
	}

	public int getHash(String str) {
		return getHash(str, 0);
	}

	public void add(String str) throws NoPlaceInTableException {
		for (int i = 0; i < lengthOfArray; i++) {
			int index = getHash(str, i);
			if (table[index] == null) {
				table[index] = str;
				return;
			}
		}
		throw new NoPlaceInTableException();
	}

	//returns index or throws exception that there is no such element
	public int getIndexInTable(String str) throws NoSuchElementException {
		for (int i = 0; i < lengthOfArray; i++) {
			int index = getHash(str, i);
			if (str.equals(table[index])) {
				return index;
			}
		}
		throw new NoSuchElementException();
	}
}
