/**
 * 
 */
package com.biswal.anshuman.practise;

/**
 * This class is used to find the fibonacci of nth number using recursion
 * 0,1,1,2,3,5 so fibonacci(5)=3 i.e the 5th number of fibonacci series is 3
 * 
 * @author abiswal
 *
 */
public class Fibonacci {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long value = fibonacci(5);
		System.out.println("Fibonacci of 5th term is " + value);

	}

	public static long fibonacci(int nthTerm) {
		// TODO Auto-generated method stub
		if (nthTerm < 1) {
			throw new IllegalArgumentException(
					"Fibonacci series can be done only for values greater than or equal to 1");
		} else if (nthTerm == 1 || nthTerm == 2) {
			return nthTerm - 1;
		} else {
			return fibonacci(nthTerm - 2) + fibonacci(nthTerm - 1);
		}

	}

}
