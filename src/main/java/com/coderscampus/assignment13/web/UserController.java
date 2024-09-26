package com.coderscampus.assignment13.web;

import java.util.Arrays;
import java.util.Set;

import com.coderscampus.assignment13.domain.Address;
import com.coderscampus.assignment13.service.AccountService;
import com.coderscampus.assignment13.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/users")
	public String getAllUsers(ModelMap model) {
		Set<User> users = userService.findAll();

		model.put("users", users);
		if (users.size() == 1) {
			model.put("user", users.iterator().next());
		}

		return "users";
	}

	@GetMapping("/register")
	public String getCreateUser (ModelMap model) {

		model.put("user", new User());

		return "user/create";
	}

	@PostMapping("/register")
	public String postCreateUser (User user) {
		Address address = new Address();
		user.setAddress(address);
		address.setUser(user);
		System.out.println(user);
		addressService.saveAddress(address);
		userService.saveUser(user);
		return "redirect:/register";
	}

	@GetMapping("/users/{userId}")
	public String getOneUser (ModelMap model, @PathVariable Long userId) {
		User user = userService.findById(userId);
		if (user == null) {
			return "redirect:/users";
		}

		model.put("users", Arrays.asList(user));
		model.put("user", user);
		model.put("address", user.getAddress());
		return "user/read";
	}
	
	@PostMapping("/users/{userId}")
	public String postOneUser (User user) {
		userService.saveUser(user);

		return "redirect:/users/"+user.getUserId();
	}

//	@PostMapping("/users/{userId}/update")
//	public String updateOneUser (@PathVariable Long userId, User user) {
//		Address address = user.getAddress();
//		userService.updateUserAddress(userId, address);
//
////		if (existingUser == null) {
////			return "redirect:/users";
////		}
////
////		existingUser.setUsername(user.getUsername());
////		existingUser.setPassword(user.getPassword());
////		existingUser.setName(user.getName());
////
////		Address address = user.getAddress();
////		if (address != null) {
////
////			if (existingUser.getAddress() != null) {
////				existingUser.getAddress().setAddressLine1(address.getAddressLine1());
////				existingUser.getAddress().setAddressLine2(address.getAddressLine2());
////				existingUser.getAddress().setCity(address.getCity());
////				existingUser.getAddress().setRegion(address.getRegion());
////				existingUser.getAddress().setCountry(address.getCountry());
////				existingUser.getAddress().setZipCode(address.getZipCode());
////			} else {
////				existingUser.setAddress(address);
////			}
////		}
//		userService.saveUser(existingUser);
//
//		return "redirect:/users/" + userId;
//	}
	
	@PostMapping("/users/{userId}/delete")
	public String deleteOneUser (@PathVariable Long userId) {
		userService.delete(userId);
		return "redirect:/users";
	}
}
