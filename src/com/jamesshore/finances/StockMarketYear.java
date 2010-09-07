package com.jamesshore.finances;

public class StockMarketYear {

	private int startingBalance;
	private InterestRate interestRate;
	private int totalWithdrawals;
	private int startingPrincipal;
	private TaxRate capitalGainsTaxRate;
	
	public StockMarketYear(int startingBalance, int startingPrincipal, InterestRate interestRate, TaxRate capitalGainsTaxRate) {
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

	public InterestRate interestRate() {
		return interestRate;
	}

	public TaxRate capitalGainsTaxRate() {
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
		return capitalGainsTaxRate.compoundTaxFor(capitalGainsWithdrawn());
	}

	public int totalWithdrawn() {
		return totalWithdrawals + capitalGainsTaxIncurred();
	}

	public int interestEarned() {
		return interestRate.interestOn(startingBalance - totalWithdrawn());
	}

	public int endingBalance() {
		return startingBalance - totalWithdrawn() + interestEarned();
	}

	public int endingPrincipal() {
		int result = startingPrincipal() - totalWithdrawals;
		return Math.max(0, result);
	}

	public StockMarketYear nextYear() {
		return new StockMarketYear(this.endingBalance(), this.endingPrincipal(), this.interestRate(), this.capitalGainsTaxRate());
	}

}
