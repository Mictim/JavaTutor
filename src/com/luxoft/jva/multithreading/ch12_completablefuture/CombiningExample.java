package com.luxoft.jva.multithreading.ch12_completablefuture;

import com.luxoft.jva.multithreading.utils.RandomSleeper;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * Created by BKuczynski on 2016-10-06.
 */
public class CombiningExample {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Supplier<String> first = () -> {
			System.out.println("START 1");
			RandomSleeper.sleep(3);
			System.out.println("END 1");
			return "1";
		};
		Supplier<Integer> second = () -> {
			System.out.println("START 2");
			RandomSleeper.sleep(2);
			System.out.println("END 2");
			return 2;
		};

		CompletableFuture<String> firstCf = supplyAsync(first);
		CompletableFuture<Integer> secondCf = supplyAsync(second);

		firstCf.thenCombine(secondCf, (l, r) -> {
			System.out.printf("I get %s and %s %n", l, r);
			RandomSleeper.sleep(4);
			return l + r;
		}).thenAcceptAsync(System.out::println);

		System.out.println("Press enter");
		scanner.nextLine();

	}
}
