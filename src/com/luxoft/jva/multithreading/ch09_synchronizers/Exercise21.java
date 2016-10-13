package com.luxoft.jva.multithreading.ch09_synchronizers;

import java.util.concurrent.CyclicBarrier;

/**
 * In this exercise we will implement simple tour guide. Tourists will go to one place - guide box and wait until group will be completed. Then they will do to tour.
 * <p>
 * <ul>
 * <li>Create class {@link GuideBox} that:
 * <ul>
 * <li>Has {@link CyclicBarrier} size of 10.</li>
 * <li>Has method {@link GuideBox#accept()} to mark that tourist has arrive.</li>
 * </ul>
 * </li>
 * <li>Class {@link Tourists} that will 'produce tourist', call accept and go sleep for few seconds.</li>
 * <li>Don't forget to add some logging</li>
 * <li>Create classes and run them.</li>
 * </ul>
 *
 * @author BKuczynski.
 */
public class Exercise21 {

	public static void main(String[] args) {
		// your code goes here
	}

}
