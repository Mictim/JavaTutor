package com.luxoft.jva008.module03;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.junit.Test;

public class CyclicBarrierTutor {
	Thread t1, t2, t3;
	CyclicBarrier barrier = new CyclicBarrier(3);

    static StringBuffer buf = new StringBuffer();
    static void log(String s) {
        buf.append(s + "\n");
    }

	class TestThread implements Runnable {
		String threadName;
		
		public TestThread(String threadName) {
			this.threadName = threadName;
		}
		
		@Override
		public void run() {
			for (int i=0; i<100; i++) {
				log(threadName + ":" + i);
				try {
					barrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void testThread() {
		t1 = new Thread(new TestThread("t1"));
		t2 = new Thread(new TestThread("t2"));
		t3 = new Thread(new TestThread("t3"));
	    System.out.println("Starting threads");
		t1.start();
		t2.start();
		t3.start();

		System.out.println("Waiting for threads");
	    try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(buf);
	}

}
