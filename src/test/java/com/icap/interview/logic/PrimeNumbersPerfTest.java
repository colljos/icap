package com.icap.interview.logic;

import java.util.function.Function;

import org.junit.Test;

import com.icap.interview.logic.PrimeNumbers.*; 

public class PrimeNumbersPerfTest {

//	public static void main(String[] args) {
//		new PrimeNumbersPerfTest().testPrimeCalculatorPerformance();
//	}
	
	@Test
	public void testPrimeCalculatorPerformance() {
		
		final int NUM_PRIMES = 500000;
		
		PrimeNumberCalculator calcJ7 = new PrimeNumberCalculator(new PreJava8PrimeCalculator());
		PrimeNumberCalculator calcJ8S = new PrimeNumberCalculator(new SimplePrimeCalculator());
//        PrimeNumberCalculator calcJ8C = new PrimeNumberCalculator(new ConcurrentPrimeCalculator());
//        PrimeNumberCalculator calcJ8C2 = new PrimeNumberCalculator(new AnotherConcurrentPrimeCalculator());
        PrimeNumberCalculator calcJ8C3 = new PrimeNumberCalculator(new AccurateConcurrentPrimeCalculator());

        // calculate 10000;
        System.out.println("Calculating " + NUM_PRIMES + " primes:");
        System.out.println("PreJava8PrimeCalculator done in: " + measurePerf(calcJ7::calculate, NUM_PRIMES) + " msecs" );
        System.out.println("SimplePrimeCalculator done in: " + measurePerf(calcJ8S::calculate, NUM_PRIMES) + " msecs" );
//        System.out.println("ConcurrentPrimeCalculator done in: " + measurePerf(calcJ8C::calculate, NUM_PRIMES) + " msecs" );
//        System.out.println("AnotherConcurrentPrimeCalculator done in: " + measurePerf(calcJ8C2::calculate, NUM_PRIMES) + " msecs" );
        System.out.println("AccurateConcurrentPrimeCalculator done in: " + measurePerf(calcJ8C3::calculate, NUM_PRIMES) + " msecs" );
	}
	
		
    public static <T, R> long measurePerf(Function<T, R> f, T input) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            f.apply(input);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }	
}
