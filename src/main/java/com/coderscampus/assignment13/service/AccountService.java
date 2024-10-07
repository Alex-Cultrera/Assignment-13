package com.coderscampus.assignment13.service;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
	
	private final AccountRepository accountRepo;

	public AccountService(AccountRepository accountRepo) {
		this.accountRepo = accountRepo;
	}

	public Account findById(Long accountId) {
		Optional<Account> accountOpt = accountRepo.findById(accountId);
		return accountOpt.orElse(new Account());
	}

	public void createDefaultUserAccounts(User user) {
		Account checking = new Account();
		checking.setAccountName("Checking Account");
		checking.getUsers().add(user);
		Account savings = new Account();
		savings.setAccountName("Savings Account");
		savings.getUsers().add(user);

		user.getAccounts().add(checking);
		user.getAccounts().add(savings);
		accountRepo.save(checking);
		accountRepo.save(savings);
	}

	public Account createNewAccountForUser(User user) {
		Account account = new Account();
		int numberOfAccounts = user.getAccounts().size();
		int nextAccount = numberOfAccounts + 1;
		account.setAccountName("Account #" + nextAccount);
		account.getUsers().add(user);
		user.getAccounts().add(account);
		accountRepo.save(account);
		return account;
	}

	public void updateExistingAccount (Account existingAccount, Account account) {
		if (existingAccount != null) {
			existingAccount.setAccountName(account.getAccountName());
			accountRepo.save(existingAccount);
		}
	}


}
