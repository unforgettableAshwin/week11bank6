package com.meritamerica.assignment5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment5.models.AccountHolder;

public interface AccountHolderRepository extends JpaRepository< AccountHolder, Integer >
{}