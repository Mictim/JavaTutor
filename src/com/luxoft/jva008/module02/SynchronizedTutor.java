package com.luxoft.jva008.module02;

import static org.junit.Assert.assertTrue;

/* 
 * In this example, 1000 threads are created which increments the counter 1000 times. The final value of the counter must be 1,000,000, but it is less (usually).
 * Please note that the counter does not grow consistently (it is noticeable at the end, and the final result is not 1,000,000).
 * Why is this happening? Try to fix it.
 */

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SynchronizedTutor {
	
	static StringBuffer buf = new StringBuffer();
	Integer counter = 0;
	
	static void log(String s) {
	    buf.append(s+"\n");
	}
	
	class TestThread implements Runnable {
		String threadName;
		
		public TestThread(String threadName) {
			this.threadName = threadName;
		}
		
		@Override
		public void run() {
			for (int i=0; i<1000; i++) {
				counter++;
				log(threadName + ":" + i + ":" + counter);
			}
		}
	}
	
	@Test
	public void testThread() {
		List<Thread> threads = new ArrayList<Thread>();
		for (int i=0; i<1000; i++) {
			threads.add(new Thread(new TestThread("t" + i)));
		}
	    System.out.println("Starting threads");
		for (int i=0; i<1000; i++) {
			threads.get(i).start();
		}
	    System.out.println("Waiting for threads");
	    try {
			for (int i=0; i<1000; i++) {
				threads.get(i).join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Uncomment this to look how counter is changing
//		System.out.println(buf);
		System.out.println("counter = " + counter);
		assertTrue(counter == 1000000);
	}

}
