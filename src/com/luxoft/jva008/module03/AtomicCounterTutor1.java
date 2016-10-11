package com.luxoft.jva008.module03;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

/* Why counter shows different values (rerun if jUnit passed successfully)?
 * How to fix this?
 * Use AtomicInteger instead of int for counter variable. Use method getAndIncrement() to increment counter.
*/ 

public class AtomicCounterTutor1 {
	final int ITERATIONS = 1000000;
	AtomicInteger counter = new AtomicInteger(0);

	class TestThread implements Runnable {
		String threadName;
		
		public TestThread(String threadName) {
			this.threadName = threadName;
		}
		
		@Override
		public void run() {
			for (int i=0; i<ITERATIONS; i++) {
				counter.getAndIncrement();
			}
		}
	}
	
	@Test
	public void testThread() {
		List<Thread> threads = new ArrayList<Thread>();
		for (int i=0; i<100; i++) {
			threads.add(new Thread(new TestThread("t"+i)));
		}
	    System.out.println("Starting threads");
		for (int i=0; i<100; i++) {
			threads.get(i).start();
		}
	    try {
			for (int i=0; i<100; i++) {
				threads.get(i).join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    System.out.println("Counter=" + counter);

        assertTrue(counter.get()==ITERATIONS*100);
	}

}
