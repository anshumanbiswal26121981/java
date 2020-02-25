/**
 * @author abiswal
 *
 */
package com.biswal.anshuman.practise.notoptimized;

/**
 * This class shall be used to find the biggest number using recursion
 * 
 * @author abiswal
 *
 */
public class BiggestNumberRecursive {


	public static int findBiggestNumber(int[] arr, int size) {
		if (arr.length <= 0) {
			throw new IllegalArgumentException("We cannot find biggest number in an empty array.");
		}
		if (size == 1) {
			return arr[0];
		} else {
			return Math.max(findBiggestNumber(arr, size - 1), arr[size-1]);
		}
		

	}

}
