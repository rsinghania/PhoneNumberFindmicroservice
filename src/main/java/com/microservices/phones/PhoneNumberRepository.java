package com.microservices.phones;

import org.springframework.data.repository.Repository;

public interface PhoneNumberRepository extends Repository<PhoneNumberDetails, Long> {
	/**
	 * Find an account with the specified account number.
	 */
	public PhoneNumberDetails findByNumber(String phoneNumber);

}
