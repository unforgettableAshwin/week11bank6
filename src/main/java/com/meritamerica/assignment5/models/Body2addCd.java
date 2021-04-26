package com.meritamerica.assignment5.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class Body2addCd
{
	public Body2addCd( double balance, CdID cd )
	{
		this.balance = balance;
		this.cdOffering = cd;
	}

	@PositiveOrZero( message = "Balance is required" )
	@NotNull( message = "Balance is required" )
	@NotBlank( message = "Balance is required" )
	double balance;

	public double getBalance()
	{
		return balance;
	}

	public void setBalance( double balance )
	{
		this.balance = balance;
	}

	public CdID getCdOffering()
	{
		return cdOffering;
	}

	public void setCdOffering( CdID cdOffering )
	{
		this.cdOffering = cdOffering;
	}

	public CdID cdOffering;
}
