package com.icap.interview.rest;

import java.util.Collection;

public class PrimeNumbersHolder {

	private int numPrimes;
	private String algorithm;
	private Collection<Integer> primes;

	public PrimeNumbersHolder(int numPrimes, String algo, Collection<Integer> primes) {
		this.numPrimes = numPrimes;
		this.algorithm = algo;
		this.primes = primes;

    }

	public int getNumPrimes() {
		return numPrimes;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public Collection<Integer> getPrimes() {
		return primes;
	}
}
