package com.icap.interview.logic;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.icap.interview.logic.PrimeNumbers.AccurateConcurrentPrimeCalculator;
import com.icap.interview.logic.PrimeNumbers.ConcurrentPrimeCalculator;
import com.icap.interview.logic.PrimeNumbers.AnotherConcurrentPrimeCalculator;
import com.icap.interview.logic.PrimeNumbers.PreJava8PrimeCalculator;
import com.icap.interview.logic.PrimeNumbers.PrimeNumberCalculator;
import com.icap.interview.logic.PrimeNumbers.SimplePrimeCalculator;

public class PrimeNumbersTest {
	
	private final List<Integer> EXP_RESULT_10 = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
	
	@Test
	public void testPreJava8PrimeCalculator() {
		
        PrimeNumberCalculator calculator = new PrimeNumberCalculator(new PreJava8PrimeCalculator());
        Collection<Integer> actual = calculator.calculate(10);
        assertEquals(EXP_RESULT_10, actual);
        
        // Boundary condition check: negative 
        actual = calculator.calculate(-10);
        assertEquals(Collections.EMPTY_LIST, actual);
        
        // Boundary condition check: MAX_INTEGER
        actual = calculator.calculate(Integer.MAX_VALUE);
        assertEquals(PrimeNumbers.MAX_PRIMES, actual.size());
	}

	@Test
	public void testSimplePrimeCalculator() {
		
        PrimeNumberCalculator calculator = new PrimeNumberCalculator(new SimplePrimeCalculator());
        Collection<Integer> actual = calculator.calculate(10);
        assertEquals(EXP_RESULT_10, actual);
        
        // Boundary condition check: negative 
        actual = calculator.calculate(-10);
        assertEquals(Collections.EMPTY_LIST, actual);
        
        // Boundary condition check: MAX_INTEGER
        actual = calculator.calculate(Integer.MAX_VALUE);
        assertEquals(PrimeNumbers.MAX_PRIMES, actual.size());      
	}
	
	@Test
	public void testConcurrentPrimeCalculator() {
		
        PrimeNumberCalculator calculator = new PrimeNumberCalculator(new ConcurrentPrimeCalculator());
        Collection<Integer> actual = calculator.calculate(10);
        assertEquals(EXP_RESULT_10, actual);
        
        // Boundary condition check: negative 
        actual = calculator.calculate(-10);
        assertEquals(Collections.EMPTY_LIST, actual);
	}
	
	@Test
	public void testAnotherConcurrentPrimeCalculator() {
		
        PrimeNumberCalculator calculator = new PrimeNumberCalculator(new AnotherConcurrentPrimeCalculator());
        Collection<Integer> actual = calculator.calculate(10);
        assertEquals(EXP_RESULT_10, actual);
        
        // Boundary condition check: negative 
        actual = calculator.calculate(-10);
        assertEquals(Collections.EMPTY_LIST, actual);
	}
	
	@Test
	public void testAccurateConcurrentPrimeCalculator() {
		
        PrimeNumberCalculator calculator = new PrimeNumberCalculator(new AccurateConcurrentPrimeCalculator());
        Collection<Integer> actual = calculator.calculate(10);
        assertEquals(EXP_RESULT_10, actual);
        
        // Boundary condition check: negative 
        actual = calculator.calculate(-10);
        assertEquals(Collections.EMPTY_LIST, actual);
        
        // Boundary condition check: MAX_INTEGER
        actual = calculator.calculate(Integer.MAX_VALUE);
        assertEquals(PrimeNumbers.MAX_PRIMES, actual.size()); 
        
	}
	
//	@Test
//	public void testExecutorPrimeCalculator() {
//		
//        PrimeNumberCalculator calculator = new PrimeNumberCalculator(new PrimeNumbersWithExecutorImpl());
//        Collection<Integer> actual = calculator.calculate(10);
//        assertEquals(EXP_RESULT_10, actual);
//	}
}
