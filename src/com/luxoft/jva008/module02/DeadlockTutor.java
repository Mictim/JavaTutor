package com.luxoft.jva008.module02;

import org.junit.Test;

/*
 * Experiment with DeadlockTutor. 
 * How the deadlock can be fixed?
 * To solve this problem, copy it into your development environment (Eclipse/IDEA).
 * When you get the deadlock you should manually stop program execution (in Eclipse use a Stop button in Debug view).
 */

public class DeadlockTutor {
	Thread t1, t2;
	Account account1 = new Account(100);
	Account account2 = new Account(50);
	
    static StringBuffer buf = new StringBuffer();
    static void log(String s) {
        buf.append(s+"\n");
    }

	class Account {
	  double balance;
	  int id;

	  public Account(double balance) {
		  this.balance = balance;
	  }
	  
	  synchronized void withdraw(double amount){
	     balance -= amount;
	  } 

	  synchronized void deposit(double amount){
	     balance += amount;
	  } 
	  
	  synchronized void transfer(Account from, double amount) {
		  // block the current account
		  deposit(amount);
		  // block the account, from which transfer is done
		  from.withdraw(amount);
	  }
	}

	@Test
	public void testDeadlock() {
		t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i=0;i<100000;i++) {
					account1.transfer(account2, 30);
					log("t1: " + i);
				}
			}
		});
		t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i=0;i<100000;i++) {
					account2.transfer(account1, 30);
					log("t2: " + i);
				}
			}			
		});
	    System.out.println("Starting threads");
		t1.start();
		t2.start();

		System.out.println("Waiting for threads");
	    try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(buf);
	}

}
