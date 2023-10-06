package com.org.exception;

public class UserInActiveException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserInActiveException(String msg) {
		super(msg);
	}
}
