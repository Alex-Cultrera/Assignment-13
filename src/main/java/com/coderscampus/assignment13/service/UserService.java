package com.coderscampus.assignment13.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.Address;
import com.coderscampus.assignment13.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AccountRepository;
import com.coderscampus.assignment13.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private AddressRepository addressRepo;
	@Autowired
	private AccountService accountService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private UserService userService;
	
	public List<User> findByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	public List<User> findByNameAndUsername(String name, String username) {
		return userRepo.findByNameAndUsername(name, username);
	}
	
	public List<User> findByCreatedDateBetween(LocalDate date1, LocalDate date2) {
		return userRepo.findByCreatedDateBetween(date1, date2);
	}
	
	public User findExactlyOneUserByUsername(String username) {
		List<User> users = userRepo.findExactlyOneUserByUsername(username);
		if (users.size() > 0)
			return users.get(0);
		else
			return new User();
	}
	
	public Set<User> findAll () {
		return userRepo.findAllUsersWithAccountsAndAddresses();
	}
	
	public User findById(Long userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		return userOpt.orElse(new User());
	}

	public User saveUser(User user) {
		if (user.getUserId() == null) {
			accountService.createDefaultUserAccounts(user);
		}
		return userRepo.save(user);
	}

	public void delete(Long userId) {
		userRepo.deleteById(userId);
	}

	public void updateUserAddress(Long userId, Address address) {
		User user = findById(userId);
		if (user != null) {
			user.setAddress(address);
			userRepo.save(user);
		}
	}

	public void updateExistingUser (User existingUser, User user) {
		if (existingUser != null) {
			existingUser.setUsername(user.getUsername());
			existingUser.setPassword(user.getPassword());
			existingUser.setName(user.getName());

			Address address = existingUser.getAddress();
			if (address != null) {
				address.setAddressLine1(user.getAddress().getAddressLine1());
				address.setAddressLine2(user.getAddress().getAddressLine2());
				address.setCity(user.getAddress().getCity());
				address.setRegion(user.getAddress().getRegion());
				address.setCountry(user.getAddress().getCountry());
				address.setZipCode(user.getAddress().getZipCode());

				addressService.saveAddress(address);
			}
			user = existingUser;
			userService.saveUser(user);
		}
	}


}
