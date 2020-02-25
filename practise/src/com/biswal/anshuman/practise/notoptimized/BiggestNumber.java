/**
 * @author abiswal
 *
 */
package com.biswal.anshuman.practise.notoptimized;

/**
 * This class shall be used to find the biggest number in an array in an unoptimized way
 * O(n)
 * @author abiswal
 *
 */
public class BiggestNumber {
	
	public static int findBiggestNumber(int[] arr) {
		if (arr.length <= 0) {
			throw new IllegalArgumentException("We cannot find biggest number in an empty array.");
		}
		
		// Store the first element of array in a variable
		int biggestNumber = arr[0];
		
		//loop through the array from the 1th element to the end of the array
		// check if the arr[i] is greater than the biggest number
		// if yes then change the variable to hold the new biggest number i.e arr[i]
		for (int i = 1; i < arr.length - 1; i++) {
			if (arr[i] > biggestNumber) {
				biggestNumber = arr[i];
			}
		}
		
		return biggestNumber;
	}

}
