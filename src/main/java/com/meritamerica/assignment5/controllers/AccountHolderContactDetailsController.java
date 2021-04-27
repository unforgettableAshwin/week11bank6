package com.meritamerica.assignment5.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment5.models.AccountHoldersContactDetails;
import com.meritamerica.assignment5.repository.AccountHolderContactDetailsRepository;

@RestController
@RequestMapping( "/AHCD" )
public class AccountHolderContactDetailsController
{
	private AccountHolderContactDetailsRepository ahcdr;

	public AccountHolderContactDetailsController( AccountHolderContactDetailsRepository ahcdr )
	{ this.ahcdr = ahcdr; }

	@GetMapping( value = "/all" )
	public List< AccountHoldersContactDetails > getAHCD()
	{ return ahcdr.findAll(); }
}