package com.meritamerica.assignment5.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
//@Table( name = "CD_OFFERING" )
public class CdOffering
{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;
	private int term;
	private double interestRate;

	public void setTerm( int term )
	{ this.term = term; }

	public void setInterestRate( double interestRate )
	{ this.interestRate = interestRate; }

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "offering")
	private List< CdAccount > cdas;

	public CdOffering()
	{}

	public CdOffering( int term, double interestRate )
	{
		this.term = term;
		this.interestRate = interestRate;
	}

	public int getId()
	{ return id; }

	public void setId( int id )
	{ this.id = id; }

	public List< CdAccount > getCdas()
	{ return cdas; }

	public void setCdas( List< CdAccount > cdas )
	{ this.cdas = cdas; }

	public int getTerm()
	{ return this.term; }

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