package com.luxoft.jva.multithreading.ch04_synchronization;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * In this exercise we will:
 * <ul>
 * <li>Create class {@link PostOffice} that:
 * <ul>
 * <li>Has a {@link Collection} of @{link Mail}s.</li>
 * <li>Collection has maximum size of 10.</li>
 * <li>Has methods {@link PostOffice#accept} and {@link PostOffice#spend} that takes mail and return mail.</li>
 * <li>Method {@link PostOffice#accept} will wait if collection has maximum size.</li>
 * <li>Method {@link PostOffice#spend} will wait if collection is empty.</li>
 * </ul>
 * </li>
 * <li>Create class {@link Postman} that implements {@link Runnable} and has reference to {@link PostOffice}
 * <ul>
 * <li>Postman will takes messages from post office.</li>
 * </ul>
 * </li>
 * <li>Create class {@link LogisticCenter} that implements {@link Runnable} and has reference to {@link PostOffice}
 * <ul>
 * <li>Logistic center will send messages from post office.</li>
 * </ul>
 * </li>
 * <li>Setup post office, logistic center and postman then run it.</li>
 * </ul>
 * <p>
 * Questions:
 * <ul>
 * <li>How you will synchronize methods in PostOffice?</li>
 * <li>What will happen if you increase number of postmen?</li>
 * <li>What will happen if you increase number of logistic centers?</li>
 * </ul>
 *
 * @author BKuczynski.
 */
public class Exercise11 {

	public static void main(String[] args) {
		System.out.printf("We started at %s\n", LocalDateTime.now());
		// your code goes here
		System.out.printf("We finished at %s\n", LocalDateTime.now());
	}

}

