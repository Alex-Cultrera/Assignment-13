package com.coderscampus.assignment13.service;

import com.coderscampus.assignment13.domain.Address;
import com.coderscampus.assignment13.repository.AddressRepository;
import com.coderscampus.assignment13.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepo;
	@Autowired
	private UserRepository userRepo;

	public List<Address> findAll () {
		return addressRepo.findAll();
	}
	
	public Address findById(Long userId) {
		Optional<Address> addressOpt = addressRepo.findById(userId);
		return addressOpt.orElse(new Address());
	}

	public Address saveAddress(Address address) {
		return addressRepo.save(address);
	}

	public void delete(Long userId) {
		addressRepo.deleteById(userId);
	}
}
