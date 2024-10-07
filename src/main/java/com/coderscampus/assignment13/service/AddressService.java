package com.coderscampus.assignment13.service;

import com.coderscampus.assignment13.domain.Address;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
	
	private final AddressRepository addressRepo;

	public AddressService(AddressRepository addressRepo) {
		this.addressRepo = addressRepo;
	}

	public void saveAddress(Address address) {
		addressRepo.save(address);
	}

	public void createNewUserAddress (User user) {
		Address address = new Address();
		address.setUser(user);
		user.setAddress(address);
		saveAddress(address);
	}


}
