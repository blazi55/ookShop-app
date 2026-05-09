package com.ookshop.application.security;

import com.ookshop.application.exceptions.AppException;
import com.ookshop.application.user.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(value = { AppException.class })
	@ResponseBody
	public ResponseEntity<ErrorDto> handleException(AppException ex) {
		return ResponseEntity
				.status(ex.getStatus())
				.body(ErrorDto.builder().message(ex.getMessage()).build());
	}
}
