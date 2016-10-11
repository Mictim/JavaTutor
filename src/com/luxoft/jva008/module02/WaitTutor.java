package com.luxoft.jva008.module02;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

/* 
 * Often it is necessary to run threads in some predefined order because the result of one thread 
 * is needed for another thread, and we need to wait until the first thread will do its job. 
 * How to make thread calls one by one, i.e. counter n of each thread should show a same number?
 * Counters should show: t1: 0, t2: 0, t1: 1, t2: 1, t1: 2, t2: 2, etc.
 * Streams may change the order, but the counter should not go ahead.
 */

public class WaitTutor {
	static StringBuffer buf = new StringBuffer();
    static void log(String s) {
        buf.append(s+"\n");
    }

	Thread t1, t2;
	Object monitor = new Object();
	int runningThreadNumber = 1;
	int t1Counter = 0, t2Counter = 0;

	class TestThread implements Runnable {
		String threadName;
		int n;
		
		public TestThread(String threadName, int n) {
			this.threadName = threadName;
			this.n = n;
		}
		
		@Override
		public void run() {
			for (int i=0; i<100; i++) {
				logAndCheckCounter(threadName, i);
				Thread.yield();
			}
		}
	}
	
	@Test
	public void testThread() {
		t1 = new Thread(new TestThread("t1", 1));
		t2 = new Thread(new TestThread("t2", 2));
	    System.out.println("Starting threads");
		t1.start();
		t2.start();

		System.out.println("Waiting for threads");
	    try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(buf);
		assertFalse(wrongCounter);
	}
	
    /** 
     * This code to check for the correctness of next counter.
     * Counters should be ordered: 0, 0, 1, 1, 2, 2, etc.
     */
    boolean wrongCounter = false;
    int counter = 0;
    static final int threadsAmount = 2;
    int counterOccured = 0;

    private void logAndCheckCounter(String threadName, int c) {
    	log(threadName + ":" + c);
        if (counter != c) { 
        	wrongCounter = true;
        }
        counterOccured++;
        
        if (counterOccured == threadsAmount) {
            counter++;
            counterOccured = 0;
        }
    }

}
