package com.luxoft.jva008.module02;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/* 
 * Put the contents of the run() method in a synchronized (counter) {...} block.
 * Did we make counter growing consistently? Why? \
 * Find the mistake or look at SynchronizedTutor2 (run several times if the test produces a correct result).
 */

public class SynchronizedTutor1 {
	
	static StringBuffer buf = new StringBuffer();
	Integer counter = 0;
	Object monitor = new Object();
	
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
				synchronized(counter) {
				   counter++;
				   Thread.yield();
				}
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
