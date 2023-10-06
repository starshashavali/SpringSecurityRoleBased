package com.org.exception;

public class DuplicateEmailException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicateEmailException(String msg) {
		super(msg);
	}

}
