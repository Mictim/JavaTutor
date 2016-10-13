package com.luxoft.jva.multithreading.ch10_executor_framework;

import com.luxoft.jva.multithreading.utils.RandomSleeper;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.luxoft.jva.multithreading.utils.ThreadPoolExecutorInfoPrinter.printInfo;

/**
 * In this example we compare behaviour of different configurations of {@link java.util.concurrent.ThreadPoolExecutor}.
 * <p>
 * <ul>
 * <li>We create some number of long-running tasks.</li>
 * <li>Execute them via different executors.</li>
 * <li>Compare executors statistics</li>
 * </ul>
 *
 * @author BKuczynski
 */
public class ThreadPoolBehaviourExample {

	public static void main(String[] args) {
		ThreadPoolExecutor cached = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		ThreadPoolExecutor fixed = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
		ThreadPoolExecutor single = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);

		List<LongRunningTask> collect = Stream.generate(LongRunningTask::new).limit(40).collect(Collectors.toList());

		executeBy(cached, collect, "Cached");
		executeBy(fixed, collect, "Fixed");
		executeBy(single, collect, "Fixed");

	}

	private static void executeBy(ThreadPoolExecutor executor, List<LongRunningTask> collect, String name) {
		collect.forEach(executor::execute);
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		printInfo(executor, name);
	}
}


/**
 * Example log-running task. Will sleep random period of time up to 5s. Then print {@code .} char.
 */
class LongRunningTask implements Runnable {
	@Override
	public void run() {
		RandomSleeper.rsleep(2);
		System.out.printf(".");
	}
}