package com.luxoft.jva008.module03;

import static org.junit.Assert.assertTrue;

/*
 * Implement the compareTo() method in order. Print orders in ReadOrderThread so that order with priority==true is the first.
 */

import java.util.concurrent.PriorityBlockingQueue;

import org.junit.Test;

public class PriorityQueueTutor1 {
    static StringBuffer buf = new StringBuffer();

	PriorityBlockingQueue<Order> orderQueue = new PriorityBlockingQueue<Order>();
	Object waiter = new Object();
	
	class Order implements Comparable<Order> {
		public String title;
		public boolean priority;

		@Override
		public String toString() {
			return "Order " + title + ", priority= " + priority;
		}

		public Order(String title, boolean priority) {
			this.title = title;
			this.priority = priority;
		}

		@Override
		public int compareTo(Order o) {
			if (this.priority && !o.priority) {
				return -1;
			} else if (!this.priority && o.priority)  {
				return 1;
			}
			return 0;
		}
		
	}
	
	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	class AddOrderThread implements Runnable {
		@Override
		public void run() {
			orderQueue.put(new Order("books", false));
			sleep(10);
			orderQueue.put(new Order("table", false));
			sleep(10);
			orderQueue.put(new Order("computer", true));
			sleep(10);
			orderQueue.put(new Order("dog", false));
			
			synchronized(waiter) {
				waiter.notifyAll();
			} 
		}
	}
	
	class ReadOrderThread implements Runnable {
		int orderNum = 0;
		@Override
		public void run() {
			while(orderNum<4) {
				try {
					Order order = orderQueue.take();
					// check that first taken order has priority==true 
					if (order.priority && orderNum == 0) {
						priorityAhead = true;
					}
					log(order.toString());
					orderNum++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	static void log(String s) {
        buf.append(s + "\n");
    }
	
	boolean priorityAhead = false;
	
	@Test
	public void testName() throws Exception {
		Thread addOrderThread = new Thread(new AddOrderThread());
		Thread readOrderThread = new Thread(new ReadOrderThread());
		addOrderThread.start();
		
		synchronized(waiter) {
			waiter.wait();
		} 
		
		readOrderThread.start();
		
		addOrderThread.join();
		readOrderThread.join();
		
		assertTrue("Order marked as priority should be the first", priorityAhead);
	}


}
