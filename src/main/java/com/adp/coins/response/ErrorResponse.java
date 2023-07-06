package com.adp.coins.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data

/**
 * Instantiates a new error response.
 *
 * @param statusCode the status code
 * @param errMsg the err msg
 */
@AllArgsConstructor
public class ErrorResponse {

	/** The status code. */
	private int statusCode;
	
	/** The err msg. */
	private String errMsg;
}
