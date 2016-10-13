package com.luxoft.jva.multithreading.ch09_synchronizers;

import java.util.concurrent.CountDownLatch;

/**
 * In this exercise we will implement printer service that creates some documents in independent threads. When all of those threads finish they job, main thread will group results and make 'final print'. 4
 * <p>
 * <ul>
 * <li>Create queue of integers and fill it (around 50 elements).</li>
 * <li>Create empty queue named results.</li>
 * <li>Create instance of {@link CountDownLatch} same size as queue.</li>
 * <li>Create class {@link PartialPrinter} that:
 * <ul>
 * <li>Takes element from queue.</li>
 * <li>Go sleep for few seconds.</li>
 * <li>Put element to results queue.</li>
 * <li>Calls {@link CountDownLatch#countDown()}.</li>
 * <li>If queue has more elements replay.</li>
 * </ul>
 * </li>
 * <li>Create few threads of {@link PartialPrinter} passing to them latch and queues.</li>
 * <li>Call {@link CountDownLatch#await()}.</li>
 * <li>Sum up elements of result queue and print result to stdout.</li>
 * </ul>
 *
 * @author BKuczynski.
 */
public class Exercise20 {

	public static void main(String[] args) {
		// your code goes here
	}

}
