package com.org.exception;

public class DuplicateCarException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicateCarException(String msg) {
		super(msg);
	}

}
