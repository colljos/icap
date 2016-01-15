package com.icap.interview.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import com.icap.interview.logic.PrimeNumbers.PrimeNumberStrategy;

public class PrimeNumbersWithExecutorImpl implements PrimeNumberStrategy {

	private final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	@Override
	public Collection<Integer> calculate(int numPrimes) {

		List<Future<?>> futures = new ArrayList<>(numPrimes);
		final List<Integer> primes = new ArrayList<>(numPrimes);

		while (primes.size() < numPrimes) {

			for (int i = 1; i < numPrimes; i++) {
				
				int candidate = i + 1;
				futures.add(executor.submit(() -> {
					return isPrime(candidate) ? candidate : 0;
				}));
			}

			futures.parallelStream().forEach(future -> {
				try {
					Integer value = (Integer) future.get();
					System.out.println(value);
					if (value > 0) {
						primes.add(value);
					}
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			});
		}

		return primes.stream().sorted().collect(Collectors.toList());
	}

}
