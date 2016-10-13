package com.luxoft.jva.multithreading.ch12_completablefuture;

import com.luxoft.jva.multithreading.utils.RandomSleeper;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by BKuczynski on 2016-10-05.
 */
public class ChainingExample {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Supplier<String> first = () -> {
			System.out.println("START");
			RandomSleeper.sleep(3);
			return "1";
		};
		Function<String, Integer> second = (s) -> {
			System.out.printf("I'm going to parse %s%n", s);
			RandomSleeper.sleep(3);
			return Integer.parseInt(s);
		};


		Function<? super Integer, CompletableFuture<Integer>> third = (i) -> {
			System.out.printf("I will prepare multiplication %s%n", i);
			RandomSleeper.sleep(3);
			return CompletableFuture.supplyAsync(() -> {
				System.out.printf("I will multiply %s%n", i);
				RandomSleeper.sleep(3);
				return i * 2;
			});
		};

		Consumer<? super Integer> fourth = (i) -> {
			System.out.printf("I received %s%n", i);
			RandomSleeper.sleep(3);
			System.out.println("DONE");
		};

		CompletableFuture.supplyAsync(first)
				.thenApplyAsync(second)
				.thenComposeAsync(third)
				.thenAcceptAsync(fourth);

		System.out.println("Press enter");
		scanner.nextLine();
	}
}
