package com.jamesshore.finances;

import static org.junit.Assert.*;
import org.junit.*;


public class _SavingsAccountYearTest {

	@Test
	public void startingBalanceMatchesConstructor() {
		assertEquals(10000, newAccount().startingBalance());
	}
	
	@Test
	public void interestRateMatchesConstructor() {
		assertEquals(10, newAccount().interestRate());
	}
	
	@Test
	public void endingBalanceAppliesInterestRate() {
		assertEquals(11000, newAccount().endingBalance());
	}
	
	@Test
	public void nextYearsStartingBalanceEqualsThisYearsEndingBalance() {
		SavingsAccountYear thisYear = newAccount();
		assertEquals(thisYear.endingBalance(), thisYear.nextYear().startingBalance());
	}
	
	@Test
	public void nextYearsInterestRateEqualsThisYearsInterestRate() {
		SavingsAccountYear thisYear = newAccount();
		assertEquals(thisYear.interestRate(), thisYear.nextYear().interestRate());
	}
	
	@Test
	public void withdrawingFundsOccursAtTheBeginningOfTheYear() {
		SavingsAccountYear year = new SavingsAccountYear(10000, 10);
		year.withdraw(1000);
		assertEquals(9900, year.endingBalance());
	}
	
	@Test
	public void multipleWithdrawalsInAYear() {
		SavingsAccountYear year = new SavingsAccountYear(10000, 10);
		year.withdraw(1000);
		year.withdraw(2000);
		assertEquals(7700, year.endingBalance());
	}

	@Test
	public void startingPrincipal() {
		SavingsAccountYear year = new SavingsAccountYear(10000, 7000, 10);
		assertEquals(3000, year.startingPrincipal());
	}
	
	@Test
	public void endingPrincipal() {
		SavingsAccountYear year = new SavingsAccountYear(10000, 7000, 10);
		assertEquals("starting principal", 3000, year.startingPrincipal());
		year.withdraw(2000);
		assertEquals("ending principal", 1000, year.endingPrincipal());
	}
	
	@Test
	public void endingPrincipalNeverGoesBelowZero() {
		SavingsAccountYear year = new SavingsAccountYear(10000, 7000, 10);
		assertEquals("starting principal", 3000, year.startingPrincipal());
		year.withdraw(4000);
		assertEquals("ending principal", 0, year.endingPrincipal());
	}
	
//	@Test
//	public void withdrawingMoreThanPrincipalIncursCapitalGainsTax() {
//		SavingsAccountYear year = new SavingsAccountYear(10000, 7000, 10);
//		year.withdraw(3000);
//		assertEquals(7700, year.endingBalance());
//		year.withdraw(5000);
//		assertEquals(2000 + 200 - (1250), year.endingBalance());
//	}

	private SavingsAccountYear newAccount() {
		SavingsAccountYear account = new SavingsAccountYear(10000, 10);
		return account;
	}
	
}
