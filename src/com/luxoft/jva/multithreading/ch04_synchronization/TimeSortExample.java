package com.luxoft.jva.multithreading.ch04_synchronization;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Example of time sort. Funny usage of {@link Thread#sleep(long)}.
 *
 * @author BKuczynski.
 */
public class TimeSortExample {

	public static void main(String[] args) {
		Random r = new Random();
		IntStream.generate(() -> r.nextInt(10))
				.limit(10)
				.mapToObj(i -> new Thread(() -> {
					try {
						TimeUnit.SECONDS.sleep(i);
						System.out.println(i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}))
				.forEach(Thread::start);
	}
}