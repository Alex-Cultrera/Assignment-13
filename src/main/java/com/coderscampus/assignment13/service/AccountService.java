package com.coderscampus.assignment13.service;

import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.repository.UserRepository;
import com.coderscampus.assignment13.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private UserRepository userRepo;
	
//	public List<Account> findByAccountName(String accountName) {
//		return accountRepo.findByAccountName(accountName);
//	}
//
//	public List<Account> findByNameAndAccountname(String name, String accountName) {
//		return accountRepo.findByNameAndAccountname(name, accountName);
//	}
//
//	public List<Account> findByCreatedDateBetween(LocalDate date1, LocalDate date2) {
//		return accountRepo.findByCreatedDateBetween(date1, date2);
//	}
//
//	public Account findExactlyOneAccountByAccountname(String accountName) {
//		List<Account> accounts = accountRepo.findExactlyOneAccountByAccountname(accountName);
//		if (accounts.size() > 0)
//			return accounts.get(0);
//		else
//			return new Account();
//	}
	
	public List<Account> findAll () {
		return accountRepo.findAll();
	}
	
	public Account findById(Long accountId) {
		Optional<Account> accountOpt = accountRepo.findById(accountId);
		return accountOpt.orElse(new Account());
	}

	public Account saveAccount(Account account) {
		return accountRepo.save(account);
	}

	public void delete(Long accountId) {
		accountRepo.deleteById(accountId);
	}
}
