package com.icap.interview.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeNumbers {
	
    public interface PrimeNumberStrategy {
        public Collection<Integer> calculate(int numPrimes);
        
    	default boolean isPrime(final int candidate) {
    		
    		if (candidate < 2) return false;
    		for (int i = 2; i * i <= candidate; ++i) {
    			if (candidate % i == 0) {
    				return false;
    			}
    		}
    		return true;
    	}
    	
//        default boolean isPrime(final int candidate) {
//        	return IntStream.range(2, candidate)
//        					.allMatch(x -> (candidate % x) != 0);
//        }
    }

    public static class PrimeNumberCalculator {
        private final PrimeNumberStrategy strategy;
        public PrimeNumberCalculator(PrimeNumberStrategy v){
            this.strategy = v;
        }
        public Collection<Integer> calculate(int numPrimes){
            return strategy.calculate(numPrimes); 
        }
    }


    public static class PreJava8PrimeCalculator implements PrimeNumberStrategy {

		public Collection<Integer> calculate(int numPrimes) {
			
			if (numPrimes < 1) 
				return Collections.emptyList();
			if (numPrimes > MAX_PRIMES) 
				numPrimes = MAX_PRIMES;
				
			List<Integer> primes = new ArrayList<>(numPrimes);
			
		    int num = 2;
		    while (primes.size() < numPrimes) {
		        if (isPrime(num))
		            primes.add(num);
		        num++;
		    }
		    return primes;
		}
		
		@Override
		// default interface implementation
		public boolean isPrime(final int candidate) { 			
			int sqrt = (int) Math.sqrt(candidate) + 1; 
			for (int i = 2; i < sqrt; i++) { 
				if (candidate % i == 0) 
					return false; 
			}				
			return true;
		}
    }
    
    public static class SimplePrimeCalculator implements PrimeNumberStrategy {
    	
        public Collection<Integer> calculate(int numPrimes) {
        	
			if (numPrimes < 1) 
				return Collections.emptyList();
			if (numPrimes > MAX_PRIMES) 
				numPrimes = MAX_PRIMES;
			
        	final List<Integer> primes = new ArrayList<>(numPrimes);
            IntStream.iterate(2, i -> i + 1).
            		filter(this::isPrime).
                    limit(numPrimes).
                    forEach(primes::add);
            return primes;
        }
    }
    
    public static class ConcurrentPrimeCalculator implements PrimeNumberStrategy {
    	
        public Collection<Integer> calculate(int numPrimes){
        	
        	int maxPrimes; 
			if (numPrimes < 1) 
				return Collections.emptyList();
			if (numPrimes > MAX_PRIMES) 
				maxPrimes = MAX_PRIMES;
			else
				maxPrimes = numPrimes;
        	
        	final ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        	final List<Integer> candidates = IntStream.range(2, 100000).boxed().collect(Collectors.toList());
        	List<Integer> primeNumbers;
			try {
				primeNumbers = forkJoinPool.submit(() -> candidates.
																	parallelStream().
																	filter(this::isPrime).
																	limit(maxPrimes).
																	collect(Collectors.toList())).
																	get();
				return primeNumbers;
			} catch (InterruptedException | ExecutionException e) {
				return Collections.emptyList();
			}
        }   
    }

   public static class AnotherConcurrentPrimeCalculator implements PrimeNumberStrategy {
    	
        public Collection<Integer> calculate(int numPrimes) {
        	
        	int maxPrimes; 
			if (numPrimes < 1) 
				return Collections.emptyList();
			if (numPrimes > MAX_PRIMES) 
				maxPrimes = MAX_PRIMES;
			else
				maxPrimes = numPrimes;
        	
        	final List<Integer> candidates = IntStream.range(2, 100000).boxed().collect(Collectors.toList());
			return candidates.
							parallelStream().
							filter(this::isPrime).
							limit(maxPrimes).
							collect(Collectors.toList());
        }   
    }
   
   public static class AccurateConcurrentPrimeCalculator implements PrimeNumberStrategy {
   	
       public Collection<Integer> calculate(int numPrimes) {
       	
       		int maxPrimes; 
			if (numPrimes < 1) 
				return Collections.emptyList();
			if (numPrimes > MAX_PRIMES) 
				maxPrimes = MAX_PRIMES;
			else
				maxPrimes = numPrimes;
			
//			IntStream.generate(new AtomicInteger()::getAndIncrement)
//								.limit(numPrimes)
//								.forEach(System.out::println);

			final List<Integer> candidates = IntStream.generate(new AtomicInteger()::getAndIncrement).
					filter(this::isPrime).
					peek(candidate -> System.out.print(candidate + ", ")).
					limit(maxPrimes).
					boxed().collect(Collectors.toList());
			return candidates;

       }   
   }
   
	// Cap maximum number of calculable primes
	public static final int MAX_PRIMES = 100000;
	
	public static List<Integer> validate(int numPrimes) {
		
		if (numPrimes < 1) 
			return Collections.emptyList();
		if (numPrimes > MAX_PRIMES) 
			return new ArrayList<>(MAX_PRIMES);
			
		return new ArrayList<>(numPrimes);
	}
}
