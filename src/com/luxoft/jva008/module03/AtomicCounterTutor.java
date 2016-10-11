package com.luxoft.jva008.module03;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

/* Why counter shows different values (rerun if jUnit passed successfully)?
 * How to fix this?
 * Use AtomicInteger instead of int for counter variable. Use method getAndIncrement() to increment counter.
*/ 

public class AtomicCounterTutor {
	final int ITERATIONS = 1000000;
	int counter=0;

	class TestThread implements Runnable {
		String threadName;
		
		public TestThread(String threadName) {
			this.threadName = threadName;
		}
		
		@Override
		public void run() {
			for (int i=0; i<ITERATIONS; i++) {
				counter++;
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

        assertTrue(counter==ITERATIONS*100);
	}


}
