package com.meritamerica.assignment5.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BankAccount
{
	public BankAccount( double balance, double interestRate )
	{
		this.balance = balance;
		this.interestRate = interestRate;
	}

	public BankAccount( double balance, double interestRate, java.util.Date accountOpenedOn )
	{
		this.balance = balance;
		this.interestRate = interestRate;
		this.date = accountOpenedOn;
	}

	public BankAccount( long accountNumber, double balance, double interestRate, java.util.Date accountOpenedOn )
	{
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.interestRate = interestRate;
		this.date = accountOpenedOn;
	}

	public java.util.Date getOpenedOn()
	{ return this.date; }

	public long getAccountNumber()
	{ return this.accountNumber; }

	public double getBalance()
	{ return this.balance; }

	public double getInterestRate()
	{ return this.interestRate; }

	public boolean withdraw( double amount )
	{
		if( ( balance - amount ) >= 0 )
		{
			balance -= amount;
			return true;
		}
		return false;
	}

	public boolean deposit( double amount )
	{
		if( amount > 0 )
		{
			this.balance += amount;
			return true;
		}
		else
		{
			return false;
		}
	}

	public double futureValue( int years )
	{
		double futureVal = MeritBank.recursiveFutureValue( this.getBalance(), years, this.getInterestRate() );
		return futureVal;
	}

	public String writeToString()
	{
		String accountString = getAccountNumber() + "," + getBalance() + "," + getInterestRate() + "," + getOpenedOn();
		return accountString;
	}

	@Override
	public String toString()
	{
		String toStr = "Acct Num " + getAccountNumber() + " balance " + getBalance();
		return toStr;
	}

	// private static long nextAccountNumber = 0;
	protected double balance;
	protected double interestRate;
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	protected long accountNumber;
	protected java.util.Date date;
}