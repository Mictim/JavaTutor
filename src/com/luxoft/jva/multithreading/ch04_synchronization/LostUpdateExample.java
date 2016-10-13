package com.luxoft.jva.multithreading.ch04_synchronization;

/**
 * Example of lost update problem – concurrent modification of not synchronized resource.
 *
 * @author BKuczynski.
 */
public class LostUpdateExample {

	public static void main(String[] args) {
		// initial state
		Amount amount = new Amount(1000L);
		CashMachine cashMachine = new CashMachine(amount);
		TaxCollector taxCollector = new TaxCollector(amount);

		System.out.printf("Initial state of amount is %s\n", amount);

		cashMachine.start();
		taxCollector.start();

		try {
			cashMachine.join();
			taxCollector.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// final state
		System.out.printf("Final state of amount is %s\n", amount);

	}


	static class Amount {

		private long value = 0L;

		public Amount(long value) {
			this.value = value;
		}

		public void save(long amount) {
			value += amount;
		}

		public void withdraw(long amount) {
			value -= amount;
		}

		@Override
		public String toString() {
			final StringBuffer sb = new StringBuffer("Amount{");
			sb.append("value=").append(value);
			sb.append('}');
			return sb.toString();
		}
	}

	static class CashMachine extends Thread {

		private Amount amount;

		public CashMachine(Amount amount) {
			super("CashMachine");
			this.amount = amount;
		}

		@Override
		public void run() {
			super.run();
			for (int i = 0; i < 100000; i++) {
				amount.withdraw(1L);
			}
		}
	}

	static class TaxCollector extends Thread {

		private Amount amount;

		public TaxCollector(Amount amount) {
			super("Incomes");
			this.amount = amount;
		}

		@Override
		public void run() {
			super.run();
			for (int i = 0; i < 100000; i++) {
				amount.save(1L);
			}
		}
	}
}