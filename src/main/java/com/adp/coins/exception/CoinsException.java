package com.adp.coins.exception;

public class CoinsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4583817803695093931L;

	public CoinsException(String errMsg) {
		super(errMsg);
	}

	public CoinsException(String errMsg, Throwable ex) {
		super(errMsg, ex);
	}
}
