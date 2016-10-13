package com.luxoft.jva.multithreading.ch12_completablefuture;

import com.luxoft.jva.multithreading.utils.RandomSleeper;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * Created by BKuczynski on 2016-10-06.
 */
public class CompetitionExample {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Supplier<String> first = () -> {
			System.out.println("START 1");
			RandomSleeper.rsleep(3);
			return "1";
		};
		Supplier<String> second = () -> {
			System.out.println("START 2");
			RandomSleeper.rsleep(4);
			return "2";
		};


		CompletableFuture<String> firstCf = CompletableFuture.supplyAsync(first);
		CompletableFuture<String> secondCf = CompletableFuture.supplyAsync(second);


		CompletableFuture.anyOf(firstCf, secondCf)
				.thenAcceptAsync(System.out::println);

		System.out.println("Press enter");
		scanner.nextLine();

	}
}
