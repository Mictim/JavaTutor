package com.luxoft.jva.multithreading.ch05_deadlocks;

/**
 * Example of deadlock problem – threads blocks each other because they synchronize on the same mutexes but in different order.
 *
 * @author BKuczynski.
 */
public class DeadlockExample {

	public static void main(String[] args) {
		String mutex1 = "Mutex 1";
		String mutex2 = "Mutex 2";
		Thread job1 = new Thread(new Job(mutex1, mutex2));
		Thread job2 = new Thread(new Job(mutex2, mutex1));

		job1.start();
		job2.start();

		try{
			job1.join();
			job2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Job implements Runnable {

	private final Object mutex1;
	private final Object mutex2;

	Job(Object mutex1, Object mutex2) {
		this.mutex1 = mutex1;
		this.mutex2 = mutex2;
	}

	@Override
	public void run() {
		System.out.printf("Thread %s try to take lock on %s\n", Thread.currentThread().getName(), mutex1);
		synchronized (mutex1) {
			System.out.printf("Thread %s takes lock on %s\n", Thread.currentThread().getName(), mutex1);

			System.out.printf("Thread %s try to take lock on %s\n", Thread.currentThread().getName(), mutex2);
			synchronized (mutex2) {
				System.out.printf("Thread %s takes lock on %s\n", Thread.currentThread().getName(), mutex2);

			}
			System.out.printf("Thread %s released lock on %s\n", Thread.currentThread().getName(), mutex2);
		}
		System.out.printf("Thread %s released lock on %s\n", Thread.currentThread().getName(), mutex1);
	}
}
