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
		Account account = accountService.createNewAccountForUser(user);
		userService.saveUser(user);
		model.put("user", user);
		model.put("account", account);
		return "account/create";
	}

	@PostMapping("/users/{userId}/account/{accountId}")
	public String postCreateAccount(ModelMap model, @PathVariable Long userId, @PathVariable Long accountId, Account account) {
		User user = userService.findById(userId);
		Account existingAccount = accountService.findById(accountId);
		accountService.updateExistingAccount(existingAccount, account, user);

		model.put("user", user);
		model.put("account", account);
		return "account/read";
	}

//	@PostMapping("/users/{userId}/account/{accountId}")
//	public String postCreateAccount(@PathVariable Long userId, User user, @PathVariable Long accountId, Account account) {
//		User existingUser = userService.findById(userId);
//		userService.updateExistingUser(existingUser, user);
//		Account existingAccount = accountService.findById(accountId);
//		accountService.updateExistingAccount(existingAccount, account, user);
//		return "account/read";
//	}

	@PostMapping("/users/{userId}/account/{accountId}/update")
	public String postSaveAccount(ModelMap model, @PathVariable Long userId, @PathVariable Long accountId) {
		User user = userService.findById(userId);
		Account account = accountService.findById(accountId);

		model.put("user", user);
		model.put("account", account);

		return "redirect:/users/" + userId + "/account/" + accountId;
	}






//	@PostMapping("/users/{userId}/account")
//	public String postCreateAccountFromOneUser(ModelMap model, @PathVariable Long userId, @PathVariable Long accountId) {
//		Account account = accountService.findById(accountId);
//		User user = userService.findById(userId);
//		if (account == null) {
//			return "redirect:/users/{userId}";
//		}
//		model.put("user", user);
//		model.put("account", account);
//		return "account/read";
//	}
//
//	@PostMapping("/users/{userId}/account/{accountId}/update")
//	public String postSaveAccount(ModelMap model, @PathVariable Long userId, @PathVariable Long accountId) {
//		User user = userService.findById(userId);
//		Account account = accountService.findById(accountId);
//
//		model.put("user", user);
//		model.put("account", account);
//
//		return "redirect: account/read";
//	}
////
//	@GetMapping("/users/{userId}/account/{accountId}")
//	public String getOneAccount(ModelMap model, @PathVariable Long userId, @PathVariable Long accountId) {
//		User user = userService.findById(userId);
//		Account account = accountService.findById(accountId);
////		Account existingAccount = accountService.findById(accountId);
////		accountService.updateExistingAccount(existingAccount, account, user);
////		userService.saveUser(user);
//		model.put("user", user);
//		model.put("account", account);
//		return "account/read";
//	}


}
