package com.adp.coins.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

	private int statusCode;
	
	private String errMsg;
}
