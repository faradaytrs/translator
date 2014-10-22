package com.hashtables;

import com.hashtables.exceptions.NoPlaceInTableException;
import com.hashtables.exceptions.NoSuchElementException;

/**
 * Created by Andrey Izotov on 03.10.2014.
 */
public class HashTable {

	private final String[] table;

	private int lengthOfArray;

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

	public String getByIndex(int index) {
		return table[index];
	}

	private int getHash(String str) {
		int length = str.length();
		long index = 0;
		for (int i = 0; i < length; i++) {
			index += (int)str.charAt(i)*(i%3);
		}
		//index += step;

		index %= lengthOfArray;

		return (int)index;
	}

	// 5 size of array
	//"sparrow" => 3
	//"bird" => 4

	//

	public int add(String str) throws NoPlaceInTableException {

		int index = getHash(str);

		for (int i = 0; i < lengthOfArray; i++) {

			if (table[index + i] == null) {

				table[index + i] = str;
				return index;

			} else if (table[index + i].equals(str)) {
				return index;
			}

		}
		throw new NoPlaceInTableException();
	}

	//returns index or throws exception that there is no such element
	public int getIndexInTable(String str) throws NoSuchElementException {

		int index = getHash(str);

		for (int i = 0; i < lengthOfArray; i++) {

			if (str.equals(table[index + i])) {
				return index;
			}

		}
		throw new NoSuchElementException();
	}
}
