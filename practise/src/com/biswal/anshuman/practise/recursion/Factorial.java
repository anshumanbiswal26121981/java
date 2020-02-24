/**
 * @author abiswal
 *
 */

package com.biswal.anshuman.practise.recursion;

/**
 * This class is used to find the factorial of a number using recursion
 * @author abiswal
 *
 */
public class Factorial {

	public static void main(String[] args) {
		long factorialValue = factorial(10);
		System.out.println("Factorial of 10 is " + factorialValue);
	}

	public static long factorial(int i) {
		if (i < 0) {
			throw new IllegalArgumentException(
					"Factorial can be found only for number which is greater than or equal to 0");
		}
		if (i == 0 || i == 1) {
			return 1;
		} else {
			return i * factorial(i - 1);
		}
	}

}
