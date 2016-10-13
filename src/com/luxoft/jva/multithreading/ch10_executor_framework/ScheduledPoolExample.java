package com.luxoft.jva.multithreading.ch10_executor_framework;

import com.luxoft.jva.multithreading.utils.RandomSleeper;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.luxoft.jva.multithreading.utils.ThreadPoolExecutorInfoPrinter.printInfo;

/**
 * in this example we will play with {@link java.util.concurrent.ScheduledThreadPoolExecutor}.
 *
 * @author BKuczynski
 */
public class ScheduledPoolExample {

	public static void main(String[] args) {
		ScheduledThreadPoolExecutor scheduled = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(5);
		List<SimpleScheduledTask> collect = Stream.generate(SimpleScheduledTask::new).limit(40).collect(Collectors.toList());

		executeBy(scheduled, collect, "Scheduled");

	}

	private static void executeBy(ScheduledThreadPoolExecutor executor, List<SimpleScheduledTask> collect, String name) {
		collect.forEach(t -> executor.schedule(t, 1L, TimeUnit.SECONDS));

		try {
			executor.shutdown();
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (!executor.isTerminated()) {
		}
		printInfo(executor, name);
	}
}

class SimpleScheduledTask implements Runnable {

	@Override
	public void run() {
		RandomSleeper.sleep(2);
		System.out.printf(".");
	}
}