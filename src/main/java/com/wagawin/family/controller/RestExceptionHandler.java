package com.wagawin.family.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

/**
 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
 *
 *
 */
@ControllerAdvice(assignableTypes = {HouseController.class,ChildController.class, ParentSummaryController.class})
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseBody
	@ExceptionHandler({EntityNotFoundException.class,HouseNotFoundException.class,ChildNotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> objectNotFoundHandler(EntityNotFoundException ex,HttpServletRequest request) {
		ErrorResponseEntity apiError = new ErrorResponseEntity(HttpStatus.NOT_FOUND.value(),ex.getMessage());
		apiError.setPath(request.getRequestURI());
		return buildResponseEntity(apiError, HttpStatus.NOT_FOUND);
	}

	@ResponseBody
	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> objectNotFoundHandler(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
		ErrorResponseEntity apiError = new ErrorResponseEntity(HttpStatus.BAD_REQUEST.value(),"Invalid Input Argument");
		apiError.setPath(request.getRequestURI());
		return buildResponseEntity(apiError, HttpStatus.BAD_REQUEST);
	}


	private ResponseEntity<Object> buildResponseEntity(ErrorResponseEntity apiError, HttpStatus status) {
		return new ResponseEntity<>(apiError, status);
	}
}