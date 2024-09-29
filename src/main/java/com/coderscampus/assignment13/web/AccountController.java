package com.coderscampus.assignment13.web;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.service.AccountService;
import com.coderscampus.assignment13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private UserService userService;

	@GetMapping("/users/{userId}/account")
	public String getCreateAccount(ModelMap model, @PathVariable Long userId) {
		User user = userService.findById(userId);
		model.put("user", user);
		model.put("account", accountService.createNewAccountForUser(user));
		return "account/create";
	}

	@GetMapping("/users/{userId}/account/{accountId}")
	public String getOneAccount(ModelMap model, @PathVariable Long userId, @PathVariable Long accountId) {
		User user = userService.findById(userId);
		Account account = accountService.findById(accountId);
		model.put("user", user);
		model.put("account", account);
		return "account/read";
	}

	@PostMapping("/users/{userId}/account/{accountId}")
	public String postCreateAccount(@PathVariable Long userId, Account account, @PathVariable Long accountId) {
		Account existingAccount = accountService.findById(accountId);
		// I think below line is throwing stuff off
		accountService.updateExistingAccount(existingAccount, account);
		return "redirect:/users/" + userId + "/account/" + accountId;
	}


}
