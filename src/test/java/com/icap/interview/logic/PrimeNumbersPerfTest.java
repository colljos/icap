package com.icap.interview.logic;

import java.util.function.Function;

import org.junit.Test;

import com.icap.interview.logic.PrimeNumbers.ConcurrentPrimeCalculator;
import com.icap.interview.logic.PrimeNumbers.PreJava8PrimeCalculator;
import com.icap.interview.logic.PrimeNumbers.PrimeNumberCalculator;
import com.icap.interview.logic.PrimeNumbers.SimplePrimeCalculator;

public class PrimeNumbersPerfTest {

//	public static void main(String[] args) {
//		new PrimeNumbersPerfTest().testPrimeCalculatorPerformance();
//	}
	
	@Test
	public void testPrimeCalculatorPerformance() {
		
		PrimeNumberCalculator calcJ7 = new PrimeNumberCalculator(new PreJava8PrimeCalculator());
		PrimeNumberCalculator calcJ8S = new PrimeNumberCalculator(new SimplePrimeCalculator());
        PrimeNumberCalculator calcJ8C = new PrimeNumberCalculator(new ConcurrentPrimeCalculator());

        // calculate 10000;
        System.out.println("PreJava8PrimeCalculator done in: " + measurePerf(calcJ7::calculate, 10000) + " msecs" );
        System.out.println("SimplePrimeCalculator done in: " + measurePerf(calcJ8S::calculate, 10000) + " msecs" );
        System.out.println("ConcurrentPrimeCalculator done in: " + measurePerf(calcJ8C::calculate, 10000) + " msecs" );
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
