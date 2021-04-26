package com.meritamerica.assignment5.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class AccountHoldersContactDetails
{
	@OneToOne( cascade = CascadeType.ALL )
	@JoinColumn( name = "id", referencedColumnName = "id" )
	private AccountHolder ah;

	public AccountHoldersContactDetails()
	{}

	public Integer getId()
	{ return id; }

	public void setId( Integer id )
	{ this.id = id; }

	public String geteMail()
	{
		return eMail;
	}

	public void seteMail( String eMail )
	{
		this.eMail = eMail;
	}

	public String getPhone()
	{ return phone; }

	public void setPhone( String phone )
	{ this.phone = phone; }

	public String getAddress()
	{ return address; }

	public void setAddress( String address )
	{ this.address = address; }

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Integer id;
	private String eMail;
	private String phone;
	private String address;
}
