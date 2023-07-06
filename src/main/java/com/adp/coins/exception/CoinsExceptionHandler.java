package com.adp.coins.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.adp.coins.constants.CoinsConstants;
import com.adp.coins.response.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class CoinsExceptionHandler to handle all type of exception related to
 * application
 */
@ControllerAdvice

/** The Constant log. */
@Slf4j
public class CoinsExceptionHandler {

	/**
	 * Exception handler.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> exceptionHandler(RuntimeException ex) {
		log.info("Runtime exception: {}", ExceptionUtils.getStackTrace(ex));
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CoinsConstants.APP_ERROR));

	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> exceptionHandler(MethodArgumentTypeMismatchException ex) {
		log.info("Runtime exception: {}", ExceptionUtils.getStackTrace(ex));
		System.out.println("name"+ex.getRequiredType().getName());
		if(ex.getRequiredType().getName().equals("java.lang.Integer")) {
			return ResponseEntity.badRequest()
					.body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), CoinsConstants.ERR_MSG_2));	
		}else {			
			return ResponseEntity.badRequest()
					.body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CoinsConstants.APP_ERROR));
		}

	}

	/**
	 * Exception handler.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(CoinsException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> exceptionHandler(CoinsException ex) {
		log.info("Runtime exception: {}", ExceptionUtils.getStackTrace(ex));
		return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));

	}

}
