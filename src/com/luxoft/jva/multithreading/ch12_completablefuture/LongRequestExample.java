package com.luxoft.jva.multithreading.ch12_completablefuture;

import com.luxoft.jva.multithreading.utils.RandomSleeper;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

/**
 * Created by BKuczynski on 2016-10-05.
 */
public class LongRequestExample {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		CompletableFuture.supplyAsync(() -> {
			RandomSleeper.sleep(3);
			return "Hello World from Future";
		}).thenAcceptAsync(System.out::println);
		System.out.println("Press enter");
		scanner.nextLine();

	}


}
