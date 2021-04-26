package com.meritamerica.assignment5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment5.models.CdAccount;

public interface CdAccountRepository extends JpaRepository< CdAccount, Long >
{}