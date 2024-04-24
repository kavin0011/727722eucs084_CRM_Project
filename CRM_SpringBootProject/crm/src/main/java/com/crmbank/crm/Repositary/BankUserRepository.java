package com.crmbank.crm.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crmbank.crm.Model.BankUser;

public interface BankUserRepository extends JpaRepository<BankUser, Integer> {

}
