package com.coderscampus.assignment13.web;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/users/{userId}/account/create")
	public String getCreateAccount (ModelMap model) {
		
		model.put("account", new Account());
		
		return "account/create";
	}
	
	@PostMapping("/create")
	public String postCreateAccount (Account account) {
		System.out.println(account);
		accountService.saveAccount(account);
		return "redirect:/create";
	}
	
	@GetMapping("/accounts")
	public String getAllAccounts (ModelMap model) {
		List<Account> accounts = accountService.findAll();
		
		model.put("accounts", accounts);
		if (accounts.size() == 1) {
			model.put("account", accounts.iterator().next());
		}
		
		return "accounts";
	}
	
	@GetMapping("/accounts/{accountId}")
	public String getOneAccount (ModelMap model, @PathVariable Long accountId) {
		Account account = accountService.findById(accountId);
		model.put("accounts", Arrays.asList(account));
		model.put("account", account);
		return "read";
	}
	
	@PostMapping("/accounts/{accountId}")
	public String postOneAccount (Account account) {
		accountService.saveAccount(account);
		return "redirect:/accounts/"+account.getAccountId();
	}
	
	@PostMapping("/accounts/{accountId}/delete")
	public String deleteOneAccount (@PathVariable Long accountId) {
		accountService.delete(accountId);
		return "redirect:/accounts";
	}
}
