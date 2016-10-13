package com.luxoft.jva.multithreading.ch11_forkjoin_framework;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * In this example
 *
 * @author BKuczynski
 */
public class UpdatePriceExample {

	public static void main(String[] args) {
		List<Product> products = ProductGenerator.generate(1000000);
		ForkJoinPool pool = new ForkJoinPool();
		PriceUpdateTask task = new PriceUpdateTask(products, 0, products.size(), 0.2);
		pool.execute(task);
		do {
			System.out.printf("Main: Thread Count: %d\n", pool.getActiveThreadCount());
			System.out.printf("Main: Thread Steal: %d\n", pool.getStealCount());
			System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
			try {
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());
		pool.shutdown();
		System.out.println(products.get(1));
	}
}

class PriceUpdateTask extends RecursiveAction {

	private static final long serialVersionUID = 1L;

	private final List<Product> products;
	private final int first;
	private final int last;
	private final double increment;

	public PriceUpdateTask(List<Product> products, int first, int last, double increment) {
		this.products = products;
		this.first = first;
		this.last = last;
		this.increment = increment;
	}

	@Override
	protected void compute() {
		if (isSmallEnough()) {
			conquer();
		} else {
			divide();
		}
	}

	private void divide() {
		System.out.printf("Task: Pending tasks: %s%n", getQueuedTaskCount());
		int middle = (first + last) / 2;
		PriceUpdateTask l = new PriceUpdateTask(products, first, middle + 1, increment);
		PriceUpdateTask r = new PriceUpdateTask(products, middle + 1, last, increment);
		invokeAll(l, r);
	}

	private boolean isSmallEnough() {
		return last - first < 100;
	}

	private void conquer() {
		for (int i = first; i < last; i++) {
			Product product = products.get(i);
			product.setPrice(product.getPrice() + (int) (product.getPrice() * increment));
		}
	}

}


class Product {
	private String name;
	private int price;

	public Product(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product{" +
				"name='" + name + '\'' +
				", price=" + price +
				'}';
	}
}

class ProductGenerator {

	static List<Product> generate(int number) {
		AtomicInteger i = new AtomicInteger(1);
		return Stream.generate(() -> new Product("Product-" + i.getAndIncrement(), 10)).limit(number).collect(Collectors.toList());
	}
}