package com.meritamerica.assignment5.models;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
//@Table( name = "bankAccount" )
public class SavingsAccount extends BankAccount
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
	
	

	public SavingsAccount()
	{
		super( 0, SAVINGS_INTERESTRATE, new Date() );
	}

	public SavingsAccount( double balance )
	{
		super( balance, SAVINGS_INTERESTRATE, new Date() );
	}

	public SavingsAccount( long accountNumber, double balance, double interestRate, java.util.Date accountOpenedOn )
	{
		super( accountNumber, balance, interestRate, accountOpenedOn );
	}

	public static SavingsAccount readFromString( String accountData ) throws java.lang.NumberFormatException
	{
		StringTokenizer token = new StringTokenizer( accountData, "," );
		int numAccount = Integer.parseInt( token.nextToken() );
		double balance = Double.parseDouble( token.nextToken() );
		double rate = Double.parseDouble( token.nextToken() );

		Date date = new Date( token.nextToken() );
		Format f = new SimpleDateFormat( "dd/MM/yy" );
		String strDate = f.format( date );
		date = new Date( strDate );

		SavingsAccount savings = new SavingsAccount( numAccount, balance, rate, date );
		return savings;
	}

	private static final double SAVINGS_INTERESTRATE = 0.01;
//	private static long nextAccountNumber = 0;
}