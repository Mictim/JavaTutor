package com.luxoft.jva.multithreading.ch08_locks_and_semaphores;

/**
 * In this exercise we will implement InfoDesk:
 * <ul>
 * <li>Create class {@link Information}
 * <ul>
 * <li>Information has two int fields</li>
 * <li>Information has getters and setters</li>
 * <li>Each read and write must be synchronized but read not blocks write</li>
 * </ul>
 * </li>
 * <li>Create class {@link UpdateService} that implements {@link Runnable}.
 * <ul>
 * <li>Service will sleep random time and after then update information</li>
 * </ul>
 * </li>
 * <li>Create class {@link Client} that implements {@link Runnable}.
 * <ul>
 * <li>Client read information and print it on stdout.</li>
 * </ul>
 * </li>
 * <li>Create one instance of service and few instances of client.</li>
 * <li>Start clients and then service</li>
 * </ul>
 *
 * @author BKuczynski.
 */
public class Exercise18 {

	public static void main(String[] args) {
		// your code goes here
	}

}
