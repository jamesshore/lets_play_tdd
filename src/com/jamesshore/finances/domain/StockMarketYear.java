package com.jamesshore.finances.domain;

public class StockMarketYear {

	private Year year;
	private Dollars startingBalance;
	private Dollars startingPrincipal;
	private InterestRate interestRate;
	private TaxRate capitalGainsTaxRate;
	private Dollars totalWithdrawals;
	
	public StockMarketYear(Year year, Dollars startingBalance, Dollars startingPrincipal, InterestRate interestRate, TaxRate capitalGainsTaxRate) {
		this.year = year;
		this.startingBalance = startingBalance;
		this.startingPrincipal = startingPrincipal;
		this.interestRate = interestRate;
		this.capitalGainsTaxRate = capitalGainsTaxRate;
		this.totalWithdrawals = new Dollars(0);
	}

	public Year year() {
		return year;
	}

	public Dollars startingBalance() {
		return startingBalance;
	}

	public Dollars startingPrincipal() {
		return startingPrincipal;
	}

	private Dollars startingCapitalGains() {
		return startingBalance().minus(startingPrincipal());
	}

	public InterestRate interestRate() {
		return interestRate;
	}

	public TaxRate capitalGainsTaxRate() {
		return capitalGainsTaxRate;
	}

	public void withdraw(Dollars amount) {
		this.totalWithdrawals = totalWithdrawals.plus(amount);
	}

	private Dollars capitalGainsWithdrawn() {
		return Dollars.min(startingCapitalGains(), totalWithdrawals);
	}

	public Dollars capitalGainsTaxIncurred() {
		return capitalGainsTaxRate.compoundTaxFor(capitalGainsWithdrawn());
	}

	public Dollars totalWithdrawn() {
		return totalWithdrawals.plus(capitalGainsTaxIncurred());
	}

	public Dollars appreciation() {
		return interestRate.interestOn(startingBalance.minus(totalWithdrawn()));
	}

	public Dollars endingBalance() {
		return startingBalance.minus(totalWithdrawn()).plus(appreciation());
	}

	public Dollars endingPrincipal() {
		Dollars principalReducedBy = totalWithdrawn().subtractToZero(startingCapitalGains());
		return startingPrincipal.minus(principalReducedBy);
	}

	public StockMarketYear nextYear() {
		return new StockMarketYear(year.nextYear(), this.endingBalance(), this.endingPrincipal(), this.interestRate(), this.capitalGainsTaxRate());
	}

}
