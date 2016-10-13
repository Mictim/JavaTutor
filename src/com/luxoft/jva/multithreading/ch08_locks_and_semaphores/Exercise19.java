package com.luxoft.jva.multithreading.ch08_locks_and_semaphores;

import java.util.concurrent.Semaphore;

/**
 * In this exercise we will implement simple printer with many trays:
 * <ul>
 * <li>Create class {@link Printer} that implements {@link Runnable}.
 * <ul>
 * <li>Printer has method {@link Printer#print(PrintJob, int)}</li>
 * <li>Printer has {@link Semaphore} that manage access to tray.</li>
 * <li>Each execution of that method force printer thread to sleep some random time.</li>
 * </ul>
 * </li>
 * <li>Create class {@link PrintJob} that implements {@link Runnable}.
 * <ul>
 * <li>Job has reference to {@link Printer}.</li>
 * <li>Job may need more than one tray.</li>
 * <li>Job call {@link Printer#print(PrintJob, int)}.</li>
 * </ul>
 * </li>
 * <li>Create one instance of printer and few instances of job.</li>
 * <li>Run it and observe communicates</li>
 * </ul>
 *
 * @author BKuczynski.
 */
public class Exercise19 {

	public static void main(String[] args) {
		// your code goes here
	}

}
