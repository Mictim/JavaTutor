package com.luxoft.jva.multithreading.ch09_synchronizers;

import com.luxoft.jva.multithreading.utils.RandomSleeper;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

/**
 * This is an example of usage of {@link CountDownLatch} with environment of limited resources represented by {@link Semaphore}.
 * 
 * We have overloaded warehouse that need to send trains to achieve some free space, but  
 *
 * @author BKuczynski
 */
public class TransportTycoon {

	public static final int NUMBER_OF_TRACKS = 2;
	public static final int NUMBER_OF_TRAINS = 4;

	public static void main(String[] args) {
		TrainLine track = new TrainLine(NUMBER_OF_TRACKS);
		Thread warehouse = new Thread(new Warehouse(new CountDownLatch(NUMBER_OF_TRAINS), track));
		warehouse.start();
		try {
			warehouse.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Warehouse implements Runnable{

	private final CountDownLatch overload;
	private final TrainLine trainLine;

	Warehouse(CountDownLatch overload, TrainLine trainLine) {
		this.overload = overload;
		this.trainLine = trainLine;
	}

	@Override
	public void run() {
		System.out.println("We have too many goods in our warehouse!");
		System.out.println("We must send them to customers!");
		System.out.printf("We need %d trains to do this%n", overload.getCount());
		System.out.printf("But train line has only %d free tracks %n", trainLine.numberOfFreeTracks());
		trainLine.sendTrains((int) overload.getCount(), overload);
		try {
			overload.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Now we have some space!");
	}
}


class Train implements Runnable{

	private final Semaphore semaphore;
	private final CountDownLatch waybill;

	Train(Semaphore semaphore, CountDownLatch waybill) {
		this.semaphore = semaphore;
		this.waybill = waybill;
	}

	@Override
	public void run() {
		try {
			semaphore.acquire(1);
			departure();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		RandomSleeper.rsleep(10);
		arrival();
		semaphore.release(1);
		waybill.countDown();
	}

	private void departure() {
		System.out.printf("%s departure at  %tB %<te,  %<tY  %<tT %<Tp%n", Thread.currentThread().getName(), LocalDateTime.now());
	}

	private void arrival() {
		System.out.printf("%s arrived at  %tB %<te,  %<tY  %<tT %<Tp%n", Thread.currentThread().getName(), LocalDateTime.now());
	}
}

class TrainLine {

	private final Semaphore semaphore;

	TrainLine(int numberOfTracks) {
		semaphore = new Semaphore(numberOfTracks);
	}

	public int numberOfFreeTracks(){
		return semaphore.availablePermits();
	}

	public void sendTrains(int numberOfTrains, CountDownLatch waybill) {
		System.out.printf("Someone request for %d trains%n", numberOfTrains);
		Stream.generate(() -> new Train(semaphore, waybill))
				.limit(numberOfTrains)
				.map(Thread::new)
				.forEach(Thread::start);
		System.out.println("All trains ready to go!");
	}
}
