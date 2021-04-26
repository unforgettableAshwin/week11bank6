package com.meritamerica.assignment5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories // ( basePackages = "repository" )
@SpringBootApplication
public class Assignment5Application
{
	public static void main( String[] args )
	{
		SpringApplication.run( Assignment5Application.class, args );
	}
}