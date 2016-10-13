package com.luxoft.jva.multithreading.ch11_forkjoin_framework;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by BKuczynski on 2016-09-08.
 */
public class AsyncSearchExample {

	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		FolderProcessor fp = new FolderProcessor(new File("./../").toPath(), "Example");
		pool.execute(fp);
		do {
			System.out.printf("******************************************\n");
			System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
			System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
			System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
			System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
			System.out.printf("******************************************\n");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!fp.isDone());
		pool.shutdown();

		fp.join().forEach(System.out::println);
	}
}


class FolderProcessor extends RecursiveTask<List<Path>> {

	private static final long serialVersionUID = 1L;
	private final Path folder;
	private final String namePart;
	private final List<FolderProcessor> subs;

	public FolderProcessor(Path folder, String namePart) {
		this.folder = folder;
		this.namePart = namePart;
		subs = new ArrayList<>();
	}

	@Override
	protected List<Path> compute() {
		File[] a = folder.toFile().listFiles();
		List<File> files = Arrays.asList(a == null ? new File[]{} : a);

		List<Path> result = new ArrayList<>();
		files.forEach(f -> {
			if (f.isDirectory())
				createSubTask(f.toPath());
			else
				check(f, result);
		});

		return joinResults(result);
	}

	private List<Path> joinResults(List<Path> result) {
		subs.forEach(s -> result.addAll(s.join()));
		return result;
	}

	private void check(File f, List<Path> result) {
		if (f.getName().contains(namePart))
			result.add(f.toPath());
	}

	private void createSubTask(Path path) {
		FolderProcessor sub = new FolderProcessor(path, namePart);
		sub.fork();
		subs.add(sub);
	}
}