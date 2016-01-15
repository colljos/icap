package com.icap.interview.rest;

import java.util.Collection;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icap.interview.logic.PrimeNumbers.PrimeNumberCalculator;
import com.icap.interview.logic.PrimeNumbers.PrimeNumberStrategy;
import com.icap.interview.logic.PrimeNumbers.SimplePrimeCalculator;
import com.icap.interview.logic.PrimeNumbers.ConcurrentPrimeCalculator;
import com.icap.interview.logic.PrimeNumbers.PreJava8PrimeCalculator;

@RestController
public class PrimeNumbersController {

    @RequestMapping("/primes")
    public PrimeNumbersHolder greeting(@RequestParam(value="algo", defaultValue="SimplePrimeCalculator") String algo,
    						 @RequestParam(value="numPrimes", defaultValue="10") String numPrimes) {
    	
    	PrimeNumberStrategy algorithm;
    	switch (algo) {
			case "simple":				
				algorithm = new SimplePrimeCalculator();
				break;
			case "concurrent":				
				algorithm = new ConcurrentPrimeCalculator();
				break;
			default:
				algorithm = new PreJava8PrimeCalculator();
				break;
		}
    	
    	PrimeNumberCalculator calculator = new PrimeNumberCalculator(algorithm);
    	Collection<Integer> primes = calculator.calculate(Integer.valueOf(numPrimes));
    	System.out.println(primes);

    	return new PrimeNumbersHolder(Integer.valueOf(numPrimes), algorithm.getClass().getSimpleName(), primes);
    }
}
