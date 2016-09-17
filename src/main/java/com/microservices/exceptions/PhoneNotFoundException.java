package com.microservices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PhoneNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PhoneNotFoundException(String phoneNumber) {
		super("No such phone number: " + phoneNumber);
	}
}
