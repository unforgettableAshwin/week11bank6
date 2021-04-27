package com.meritamerica.assignment5.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment5.exceptions.InvalidRequestException;
import com.meritamerica.assignment5.exceptions.NoSuchResourceFoundException;
import com.meritamerica.assignment5.models.AccountHolder;
import com.meritamerica.assignment5.models.Body2addCd;
import com.meritamerica.assignment5.models.CdAccount;
import com.meritamerica.assignment5.models.CdOffering;
import com.meritamerica.assignment5.models.CheckingAccount;
import com.meritamerica.assignment5.models.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment5.models.ExceedsFraudSuspicionLimitException;
import com.meritamerica.assignment5.models.MeritBank;
import com.meritamerica.assignment5.models.SavingsAccount;
import com.meritamerica.assignment5.repository.AccountHolderRepository;
import com.meritamerica.assignment5.repository.CdAccountRepository;
import com.meritamerica.assignment5.repository.CdOfferingRepository;
import com.meritamerica.assignment5.repository.CheckingAccountRepository;
import com.meritamerica.assignment5.repository.SavingsAccountRepository;

@RestController
public class AccountHolderController< body2addCD >
{
	@RequestMapping( value = "/", method = RequestMethod.GET )
	public String test()
	{ return "Testing"; }

	@GetMapping( value = "/getAhs" )
	public List< AccountHolder > getAccountHolders()
	{ return ahr.findAll(); }

	@Autowired
	private AccountHolderRepository ahr;

	@Autowired
	private SavingsAccountRepository sar;

	@Autowired
	private CheckingAccountRepository car;

	@Autowired
	private CdAccountRepository cdar;

	@Autowired
	private CdOfferingRepository cdr;

//	public String getString()
//	{ return "hey"; }

	@PostMapping( value = "/AccountHolders" )
	public AccountHolder addAccountHolder( @RequestBody AccountHolder account ) throws InvalidRequestException
	{
		boolean isNull = account.getFirstName() == null || account.getLastName() == null || account.getSSN() == null;

		boolean isBlank = account.getFirstName().length() == 0 || account.getLastName().length() == 0 || account.getSSN().length() == 0;

		if( isNull || isBlank )
		{ throw new InvalidRequestException( "Invalid Request" ); }
		ahr.save( account );
		MeritBank.addAccountHolder( account );
		return account;
	}

	@GetMapping( value = "/rest/AH/all" )
	List< AccountHolder > getAll()
	{ return ahr.findAll(); }

	@GetMapping( value = "/AccountHolders/{id}" )
	public AccountHolder getAccountHolderById( @PathVariable int id ) throws NoSuchResourceFoundException
	{ return getAccountHolderByID( id ); }

	private AccountHolder getAccountHolderByID( int id ) throws NoSuchResourceFoundException
	{
		for( AccountHolder ah : ahr.findAll() )
			if( ah.getId() == id ) return ah;

		throw new NoSuchResourceFoundException( "No Such Resource Found" );
	}

	@PostMapping( value = "/AccountHolders/{id}/CheckingAccounts" )
	public CheckingAccount createNewCheckingAccount( @RequestBody CheckingAccount account, @PathVariable int id ) throws ExceedsCombinedBalanceLimitException,
			NoSuchResourceFoundException, InvalidRequestException
	{
		AccountHolder acch = ahr.getOne( id );
		account.setAh( acch );
		return car.save( account );
	}

	@GetMapping( value = "/AccountHolders/{id}/CheckingAccounts" )
	public List< CheckingAccount > getCheckingAccount( @PathVariable int id ) throws NoSuchResourceFoundException
	{ return getAccountHolderByID( id ).getCheckingAccounts(); }

	@PostMapping( value = "/AccountHolders/{id}/SavingsAccounts" )
	public SavingsAccount createNewSavingsAccount( @RequestBody SavingsAccount sa, @PathVariable int id ) throws NoSuchResourceFoundException, InvalidRequestException,
			ExceedsCombinedBalanceLimitException
	{
		AccountHolder ah = ahr.getOne( id );
		sa.setAh( ah );
		return sar.save( sa );
	}

	@GetMapping( value = "/AccountHolders/{id}/SavingsAccounts" )
	public List< SavingsAccount > getSavingsAccounts( @PathVariable int id ) throws NoSuchResourceFoundException
	{ return getAccountHolderByID( id ).getSavingsAccounts(); }

	@PostMapping( value = "/AccountHolders/{id}/CDAccounts" )
	@ResponseStatus( HttpStatus.CREATED )
	public CdAccount addCD( @RequestBody @Valid Body2addCd body, @PathVariable int id ) throws NoSuchResourceFoundException, InvalidRequestException,
			ExceedsFraudSuspicionLimitException
	{
		AccountHolder ah = ahr.getOne( id );
		CdOffering cdo = MeritBank.getCDOfferingById( body.cdOffering.getId() );
		if( body.getBalance() < 0 ) throw new InvalidRequestException( "Balance cannot be negative." );
		return getAccountHolderByID( id ).addCDAccount( cdo, body.getBalance() );
	}

	@GetMapping( value = "/AccountHolders/{id}/CDAccounts" )
	public List< CdAccount > getCDAccounts( @PathVariable int id ) throws NoSuchResourceFoundException
	{ return getAccountHolderByID( id ).getCDAccounts(); }
}