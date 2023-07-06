package com.adp.coins.exception;

/**
 * The Class CoinsException.
 */
public class CoinsException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4583817803695093931L;

	/**
	 * Instantiates a new coins exception.
	 *
	 * @param errMsg the err msg
	 */
	public CoinsException(String errMsg) {
		super(errMsg);
	}

	/**
	 * Instantiates a new coins exception.
	 *
	 * @param errMsg the err msg
	 * @param ex the ex
	 */
	public CoinsException(String errMsg, Throwable ex) {
		super(errMsg, ex);
	}
}
