package de.leuphana.backend.service;

import org.springframework.dao.DataIntegrityViolationException;

/**
 * A data integraty violation exception containing a message intended to be
 * shown to the end user.
 */
public class AccountFriendlyDataException extends DataIntegrityViolationException {

	public AccountFriendlyDataException(String message) {
		super(message);
	}

}
