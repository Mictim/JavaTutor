package com.luxoft.jva008.module03;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/*
 * Test will fail if executing for too long time.
 * Use lock.tryLock() to increase the speed of the lock.
 * Also try to use tryLock(time, timeUnit) to increase the execution time even more (try different timings).
 */

public class TryLockTutor2 {
	Thread t1, t2, t3;
	Object monitor = new Object();
	int runningThreadNumber = 1;
	StringBuffer stringBuilder = new StringBuffer("");
	public static long ITERATIONS = 100000;

    static StringBuffer buf = new StringBuffer();
    static void log(String s) {
    	buf.append(s + "\n");
    }

	class WritingThread implements Runnable {
		String threadName;
		Lock lock;

		public WritingThread(String threadName, Lock lock) {
			this.threadName = threadName;
			this.lock = lock;
		}

		@Override
		public void run() {
			for (int i = 0; i < ITERATIONS; i++) {
				try {
					if (lock.tryLock(1, TimeUnit.MICROSECONDS)) {
						lock.lock();
						stringBuilder.append(threadName);
						Thread.yield();
						stringBuilder.append(threadName);
						Thread.yield();
						stringBuilder.append(",");
						lock.unlock();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class ReadingThread implements Runnable {
		String threadName;
		Lock lock;

		public ReadingThread(String threadName, Lock lock) {
			this.threadName = threadName;
			this.lock = lock;
		}

		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
			    lock.lock();
				String s = stringBuilder.toString();
				int len = s.length();
				int l = len>50 ? len-50:0;
				log(len + ":" + s.substring(l));
				lock.unlock();
				Thread.yield();
			}
		}
	}

	@Test
	public void testThread() {
		long start = new Date().getTime();
		Lock lock = new ReentrantLock();
		t1 = new Thread(new WritingThread("1", lock));
		t2 = new Thread(new WritingThread("2", lock));
		t3 = new Thread(new ReadingThread("3", new ReentrantLock()));
		t1.start();
		t2.start();
		t3.start();

		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long time = new Date().getTime()-start;
		log("Time of work:" + time);
		
		System.out.println(buf);
		assertTrue("Too long, use tryLock()", time<100);

		// Analyze buf to check the correctness
		String[] lines = buf.toString().split("\n");
		for (int i=0; i<lines.length-1; i++) {
		    String line = lines[i];
		    String[] groups = line.split(",");
		    for (int j=1; j<groups.length-1; j++) {
		        if (!groups[j].equals("11") && !groups[j].equals("22")) {
		                fail("String is not paired: "+lines[i]);
		                break;  
		            }
		    }
		}
		
	}





}
