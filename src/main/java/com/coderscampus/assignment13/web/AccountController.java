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

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private UserService userService;

	@PostMapping("/users/{userId}/account")
	public String postCreateAccountFromOneUser(ModelMap model, @PathVariable Long userId) {
		User user = userService.findById(userId);
		Account account = accountService.createNewUserAccount(user);
		userService.saveUser(user);
		model.put("user", user);
		model.put("account", account);
		
		return "account/create";
	}

	@PostMapping("/users/{userId}/account/{accountId}")
	public String postSaveAccount(@PathVariable Long userId, User user, @PathVariable Long accountId, Account account) {
		Account existingAccount = accountService.findById(accountId);
		User existingUser = userService.findById(userId);
		accountService.updateExistingAccount(existingAccount, account, existingUser, user);
		userService.saveUser(existingUser);
//		model.put("user", existingUser);
//		model.put("account", account);
		return "account/read";
	}

	@GetMapping("/users/{userId}/account/{accountId}")
	public String getOneAccount(ModelMap model, @PathVariable Long userId, @PathVariable Long accountId) {
		User user = userService.findById(userId);
		Account account = accountService.findById(accountId);
//		Account existingAccount = accountService.findById(accountId);
//		accountService.updateExistingAccount(existingAccount, account, user);
//		userService.saveUser(user);
		model.put("user", user);
		model.put("account", account);
		return "account/read";
	}


}
