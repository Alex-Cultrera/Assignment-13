package com.coderscampus.assignment13.web;

import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.service.AccountService;
import com.coderscampus.assignment13.service.AddressService;
import com.coderscampus.assignment13.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class UserController {
	
	private final UserService userService;
	private final AccountService accountService;
	private final AddressService addressService;

	public UserController(UserService userService, AccountService accountService, AddressService addressService) {
		this.userService = userService;
		this.accountService = accountService;
		this.addressService = addressService;
	}

	@GetMapping("/users")
	public String getAllUsers(ModelMap model) {
		Set<User> users = userService.findAll();
		model.put("users", users);
		if (users.size() == 1) {
			model.put("user", users.iterator().next());
		}
		return "users";
	}

	@PostMapping("/users")
	public String postAllUsers(ModelMap model) {
		return "redirect:/users";
	}

	@GetMapping("/register")
	public String getCreateUser (ModelMap model) {
		model.put("user", new User());
		return "user/create";
	}

	@PostMapping("/register")
	public String postCreateUser (User user) {
		if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
			return "redirect:/register";
		} else {
		addressService.createNewUserAddress(user);
		accountService.createDefaultUserAccounts(user);
		userService.save(user);
		}
		return "redirect:/register";
	}

	@GetMapping("/users/{userId}")
	public String getUser (ModelMap model, @PathVariable Long userId) {
		User user = userService.findById(userId);
		if (user == null) {
			return "redirect:/users";
		}
		model.put("user", user);
		model.put("accounts", user.getAccounts());
		return "user/read";
	}

	@PostMapping("/users/{userId}/update")
	public String updateUser(@PathVariable Long userId, User user) {
		User existingUser = userService.findById(userId);
		userService.update(existingUser, user);
		return "redirect:/users/" + user.getUserId();
	}

	@PostMapping("/users/{userId}/delete")
	public String deleteUser(@PathVariable Long userId) {
		userService.delete(userId);
		return "redirect:/users";
	}
}

