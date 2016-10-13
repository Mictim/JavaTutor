package com.luxoft.jva.multithreading.ch04_synchronization;

import com.luxoft.jva.multithreading.ch04_synchronization.LostUpdateExample.CashMachine;
import com.luxoft.jva.multithreading.ch04_synchronization.LostUpdateExample.TaxCollector;

import java.time.LocalDateTime;

/**
 * Based on {@link LostUpdateExample}, change classes {@link CashMachine} and {@link TaxCollector} to avoid lost update problem.
 *
 * @author BKuczynski.
 */
public class Exercise9 {

	public static void main(String[] args) {
		System.out.printf("We started at %s\n", LocalDateTime.now());
		// your code goes here
		System.out.printf("We finished at %s\n", LocalDateTime.now());
	}

}

