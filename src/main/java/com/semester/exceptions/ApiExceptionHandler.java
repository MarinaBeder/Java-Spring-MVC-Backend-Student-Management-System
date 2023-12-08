package com.semester.exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = { NotFoundException.class })
	public ResponseEntity<Object> handleApiRequestUnAuthoizedException(NotFoundException e) {
		// 1- create payload containig exception details
		HttpStatus badRequest = HttpStatus.NOT_FOUND;
		ApiExcepion apiException = new ApiExcepion(e.getMessage(),

				badRequest, ZonedDateTime.now(ZoneId.of("Z")));
		// 2 - return response entity
		return new ResponseEntity<Object>(apiException, HttpStatus.NOT_FOUND);
	}

}
