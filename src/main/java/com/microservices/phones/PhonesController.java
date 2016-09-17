package com.microservices.phones;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.exceptions.PhoneNotFoundException;

@RestController
public class PhonesController {

	protected Logger logger = Logger.getLogger(PhonesController.class
			.getName());
	protected PhoneNumberRepository phoneRepository;

	/**
	 * 
	 * @param phoneNumRepo
	 *            An account repository implementation.
	 */
	@Autowired
	public PhonesController(PhoneNumberRepository phoneNumRepo) {
		this.phoneRepository = phoneNumRepo;

		logger.info("PhoneRepository says system has phoneNumber");
	}

	/**
	 * Fetch an account with the specified account number.
	 */
	@RequestMapping("/phone/{phoneNumber}")
	public PhoneNumberDetails byNumber(@PathVariable("phoneNumber") String phoneNumber) {

		logger.info("phone-service byNumber() invoked: " + phoneNumber);
		PhoneNumberDetails phone = phoneRepository.findByNumber(phoneNumber);
		logger.info("phone-service byNumber() found: " + phone);

		if (phone == null)
			throw new PhoneNotFoundException(phoneNumber);
		else {
			return phone;
		}
	}
}
