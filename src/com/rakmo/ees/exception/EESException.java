package com.rakmo.ees.exception;

public class EESException extends Exception{

	
	private static final long serialVersionUID = 1L;

	public EESException() {
		super();
	}

	public EESException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EESException(String message, Throwable cause) {
		super(message, cause);
	}

	public EESException(String message) {
		super(message);
	}

	public EESException(Throwable cause) {
		super(cause);
	}
	
	

}
