package com.luxoft.jva.multithreading.ch13_threadlocal;

import com.luxoft.jva.multithreading.utils.RandomSleeper;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;

/**
 *
 *
 * @author BKuczynski
 */
public class BadDataStorage {

	public static void main(String[] args) {
		BrokenStorage bs = new BrokenStorage();
		Stream.generate(() -> new Thread(bs)).limit(4)
				.peek(Thread::start)
				.peek(t -> RandomSleeper.sleep(1))
				.forEach(t -> {
				});
	}
}

class BrokenStorage implements Runnable {

	private LocalDateTime start;

	@Override
	public void run() {
		start = LocalDateTime.now();
		out.printf("%s starts at - %s%n", currentThread().getName(), start);
		RandomSleeper.rsleep(10);
		out.printf("%s starts at - %s%n", currentThread().getName(), start);
	}
}
