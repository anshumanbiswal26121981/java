/**
 * @author abiswal
 *
 */
package com.biswal.anshuman.practise.recursion;

import java.util.NoSuchElementException;

/**
 * This class is to test the binary search to search in an array
 * 
 * @author abiswal
 *
 */
public class BinarySearch {

	public static int searchIndexOfNumber(int numberToSearch, int[] array, int start, int end) {
		/*
		 * Base condition. If we have only one cell then check if the number we are
		 * searching is the only element in the array or not
		 */
		if (start == end) {
			if (array[start] == numberToSearch) {
				return start;
			} else {
				throw new NoSuchElementException("Number is not found in the list of array");
			}
		}

		/*
		 * Now find the mid of the array and check if the number searching for is less
		 * than the mid. if number is less than the mid then move left else move right
		 */
		int mid = (start + end) / 2;
		if (array[mid] == numberToSearch) {
			return mid;
		} else if (array[mid] > numberToSearch) {
			return searchIndexOfNumber(numberToSearch, array, start, mid - 1);

		} else {// (array[mid] < numberToSearch)

			return searchIndexOfNumber(numberToSearch, array, mid + 1, end);

		}
	}
}
