package com.meritamerica.assignment5.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController
{
	private static final Logger log = LoggerFactory.getLogger( LoggingController.class );

	@RequestMapping( "/l" )
	public String index()
	{
		log.trace( "A TRACE Message" );
		log.debug( "A DEBUG Message" );
		log.info( "An INFO Message" );
		log.warn( "A WARN Message" );
		log.error( "An ERROR Message" );

		return "Howdy! Check out the Logs to see the output...";
	}
}
