package com.wagawin.family.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Represents the ErrorResponseEntity response, incase of errors
 */
@Data
class ErrorResponseEntity {

	private int status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

	private String message;

	private String path;


	private ErrorResponseEntity() {
		timestamp = LocalDateTime.now();
	}

	ErrorResponseEntity(int status) {
		this();
		this.status = status;
	}
	ErrorResponseEntity(int status, String message) {
		this();
		this.status = status;
		this.message = message;
	}


}