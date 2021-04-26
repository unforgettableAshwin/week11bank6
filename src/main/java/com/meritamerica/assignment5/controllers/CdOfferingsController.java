package com.meritamerica.assignment5.controllers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment5.exceptions.InvalidRequestException;
import com.meritamerica.assignment5.exceptions.NoSuchResourceFoundException;
import com.meritamerica.assignment5.models.CdOffering;
import com.meritamerica.assignment5.models.MeritBank;

@RestController
public class CdOfferingsController
{
	ArrayList< CdOffering > cdOfferings = new ArrayList< CdOffering >();

	@ResponseStatus( HttpStatus.CREATED )
	@PostMapping( value = "/CDOfferings" )
	public CdOffering createCDOffering( @RequestBody CdOffering cdOffering ) throws InvalidRequestException
	{

		if( cdOffering.getInterestRate() <= 0 || cdOffering.getInterestRate() >= 1 )
		{
			throw new InvalidRequestException( "Invalid Request" );
		}
		if( cdOffering.getTerm() < 1 )
		{
			cdOffering = null;
			throw new InvalidRequestException( "Invalid Request" );
		}
		cdOfferings.add( cdOffering );
		MeritBank.setCDOfferings( cdOfferings.toArray( new CdOffering[0] ) );
		return cdOffering;
	}

	@GetMapping( value = "/CDOfferings" )
	public CdOffering[] getCDOfferings()
	{
		return cdOfferings.toArray( new CdOffering[0] );
	}

	@GetMapping( value = "/CDOfferings/{id}" )
	public CdOffering getCDOfferingById( @PathVariable int id ) throws NoSuchResourceFoundException
	{
		for( CdOffering cdo : MeritBank.getCDOfferings() )
			if( cdo.getId() == id ) return cdo;

		throw new NoSuchResourceFoundException( "No Such Resource Found" );
	}
}
