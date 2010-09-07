package com.jamesshore.finances;

public class StockMarketYear {

	private int startingBalance;
	private int interestRate;
	private int totalWithdrawals;
	private int startingPrincipal;
	private int capitalGainsTaxRate;
	
	public StockMarketYear(int startingBalance, int startingPrincipal, int interestRate, int capitalGainsTaxRate) {
		this.startingBalance = startingBalance;
		this.startingPrincipal = startingPrincipal;
		this.interestRate = interestRate;
		this.capitalGainsTaxRate = capitalGainsTaxRate;
		this.totalWithdrawals = 0;
	}

	public int startingBalance() {
		return startingBalance;
	}

	public int startingPrincipal() {
		return startingPrincipal;
	}

	public int interestRate() {
		return interestRate;
	}

	public int capitalGainsTaxRate() {
		return capitalGainsTaxRate;
	}

	public void withdraw(int amount) {
		this.totalWithdrawals += amount;
	}

	private int capitalGainsWithdrawn() {
		int result =  -1 * (startingPrincipal() - totalWithdrawals);
		return Math.max(0, result);
	}

	public int capitalGainsTaxIncurred() {
		double dblTaxRate = capitalGainsTaxRate / 100.0;
		double dblCapGains = capitalGainsWithdrawn();
		
		return (int)((dblCapGains / (1 - dblTaxRate)) - dblCapGains);
	}

	public int totalWithdrawn() {
		return totalWithdrawals + capitalGainsTaxIncurred();
	}

	public int interestEarned() {
		return (startingBalance - totalWithdrawn()) * interestRate / 100;
	}

	public int endingBalance() {
		int modifiedStart = startingBalance - totalWithdrawn();
		return modifiedStart + interestEarned();
	}

	public int endingPrincipal() {
		int result = startingPrincipal() - totalWithdrawals;
		return Math.max(0, result);
	}

	public StockMarketYear nextYear() {
		return new StockMarketYear(this.endingBalance(), this.endingPrincipal(), this.interestRate(), this.capitalGainsTaxRate());
	}

}
