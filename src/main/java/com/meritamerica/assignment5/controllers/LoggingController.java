package com.meritamerica.assignment5.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class LoggingController
{
	private Logger log = LoggerFactory.getLogger( this.getClass() );

	public Logger getLog()
	{ return log; }

	@RequestMapping( value = "/testLogger" )
	public String test()
	{
		log.trace( "A TRACE Message" );
		log.debug( "A DEBUG Message" );
		log.info( "An INFO Message" );
		log.warn( "A WARN Message" );
		log.error( "An ERROR Message" );

		return "Howdy! Check out the Logs to see the output...";
	}
}