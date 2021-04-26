package com.meritamerica.assignment5.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CdOffering
{
	//private static int nextId = 0;
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;
	private int term;
	private double interestRate;

	public CdOffering( int term, double interestRate )
	{
		this.term = term;
		this.interestRate = interestRate;
//		this.id = ++nextId;
	}

	public int getId()
	{ return id; }

	public void setId( int id )
	{ this.id = id; }

	public int getTerm()
	{
		return this.term;
	}

	public double getInterestRate()
	{ return this.interestRate; }

	public static CdOffering readFromString( String cdOfferingDataString ) throws java.lang.NumberFormatException
	{
		// expecting like this: 1,0.018
		CdOffering cd = null;
		if( cdOfferingDataString.indexOf( ',' ) != -1 )
		{ // if there's no ',' in the string, the string is considered as
			// NumberFormatException
			int term = Integer.parseInt( cdOfferingDataString.substring( 0, cdOfferingDataString.indexOf( ',' ) ) );
			double rate = Double.parseDouble( cdOfferingDataString.substring( cdOfferingDataString.indexOf( ',' ) + 1 ) );
			cd = new CdOffering( term, rate );
		}
		else
		{
			throw new NumberFormatException();
		}
		return cd;
	}

	public String writeToString()
	{
		String cdString = this.getTerm() + "," + this.getInterestRate();
		return cdString;
	}
}