package com.adp.coins.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adp.coins.constants.CoinsConstants;
import com.adp.coins.response.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CoinsExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> exceptionHandler(RuntimeException ex) {
		log.info("Runtime exception: {}", ExceptionUtils.getStackTrace(ex));
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CoinsConstants.APP_ERROR));

	}
	
	@ExceptionHandler(CoinsException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> exceptionHandler(CoinsException ex) {
		log.info("Runtime exception: {}", ExceptionUtils.getStackTrace(ex));
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));

	}

}
