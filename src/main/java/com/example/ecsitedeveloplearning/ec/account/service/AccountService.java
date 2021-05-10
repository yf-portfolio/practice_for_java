package com.example.ecsitedeveloplearning.ec.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecsitedeveloplearning.ec.account.model.Account;
import com.example.ecsitedeveloplearning.ec.account.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;

	public List<Account> findAll() {
		return accountRepository.findAll();
	}

}