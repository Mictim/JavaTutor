package com.luxoft.jva.multithreading.ch09_synchronizers;

import java.util.concurrent.Phaser;

/**
 * In this exercise we will implement simple file finder.
 * <p>
 * <ul>
 * <li>Create class {@link Searcher} that:
 * <ul>
 * <li>Has {@link Phaser} that will split whole task to phases.</li>
 * </ul>
 * </li>
 * <li>Create few instances of {@code Searcher} and register it in phaser.</li>
 * </ul>
 * <p>
 * Search algorithm:
 * <ul>
 * <li>Phase 1:
 * <ul>
 * <li>If current directory contains {@code *.log} files?</li>
 * <li>If contains add it to local collection.</li>
 * <li>Process subdirectories</li>
 * <li>If you found any file then go to phase 2 otherwise unregister from phaser and finish.</li>
 * </ul>
 * </li>
 * <li>Phase 2
 * <ul>
 * <li>Filter collection - find files changed in last 24h.</li>
 * <li>If you found any file then go to phase 3 otherwise unregister from phaser and finish.</li>
 * </ul>
 * </li>
 * <li>Phase 3
 * <ul>
 * <li>Print files to stdout.</li>
 * <li>Unregister from phaser.</li>
 * </ul>
 * </li>
 * </ul>
 *
 * @author BKuczynski.
 */
public class Exercise22 {

	public static void main(String[] args) {
		// your code goes here
	}

}
