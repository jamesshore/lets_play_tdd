package com.jamesshore.screencast;

public class SavingsAccount {

	private int balance = 0;
	
	public void deposit(int amount) {
		balance += amount;
	}

	public int balance() {
		return balance;
	}

	public void withdraw(int amount) {
		balance -= amount;
	}

}
