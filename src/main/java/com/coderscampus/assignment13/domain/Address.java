package com.coderscampus.assignment13.domain;

import javax.persistence.*;

@Entity
public class Address {
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	@OneToOne @MapsId @JoinColumn(name="user_id", nullable = false)
	private User user;
	@Column(length=200)
	private String addressLine1;
	@Column(length=200)
	private String addressLine2;
	@Column(length=100)
	private String city;
	@Column(length=100)
	private String region;
	@Column(length=100)
	private String country;
	@Column(length=15)
	private String zipCode;
	

	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
