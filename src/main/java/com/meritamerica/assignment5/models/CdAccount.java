package com.meritamerica.assignment5.models;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
//@Table( name = "bankAccount" )
public class CdAccount extends BankAccount
{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public CdAccount()
	{
		super( 0, 0.01, new Date() );
	}

	public CdAccount( CdOffering offering )
	{
		super( 0, offering.getInterestRate(), new Date() );
		this.offering = offering;
	}

	public CdAccount( CdOffering offering, double balance )
	{
		super( balance, offering.getInterestRate(), new Date() );
		this.offering = offering;
	}

	public CdAccount( long accountNumber, double balance, double interestRate, java.util.Date accountOpenedOn, int term )
	{
		super( accountNumber, balance, interestRate, accountOpenedOn );
		this.offering = new CdOffering( term, interestRate );
	}

	@Override
	public double getInterestRate()
	{ return this.offering.getInterestRate(); }

	public int getTerm()
	{ return this.offering.getTerm(); }

	public java.util.Date getStartDate()
	{
		Date date = new Date();
		return date;
	}

	@Override
	public boolean withdraw( double amount )
	{
		return false;
	}

	@Override
	public boolean deposit( double amount )
	{
		return false;
	}

	public double futureValue()
	{
		double futureVal = MeritBank.recursiveFutureValue( super.getBalance(), this.getTerm(), this.getInterestRate() );
		return futureVal;
	}

	public static CdAccount readFromString( String accountData ) throws java.lang.NumberFormatException
	{
		StringTokenizer token = new StringTokenizer( accountData, "," );
		int numAccount = Integer.parseInt( token.nextToken() );
		long balance = Long.parseLong( token.nextToken() );
		double rate = Double.parseDouble( token.nextToken() );

		Date date = new Date( token.nextToken() );
		Format f = new SimpleDateFormat( "dd/MM/yy" );
		String strDate = f.format( date );
		date = new Date( strDate );

		int term = Integer.parseInt( token.nextToken() );

		CdAccount cdAcc = new CdAccount( numAccount, balance, rate, date, term );
		return cdAcc;
	}

	@Override
	public String writeToString()
	{
		String cdString = getAccountNumber() + "," + getBalance() + "," + getInterestRate() + "," + getStartDate() + "," + getTerm();
		return cdString;
	}

	@Transient
	private CdOffering offering;

	public CdOffering getOffering()
	{ return offering; }

	public void setOffering( CdOffering offering )
	{ this.offering = offering; }
}
