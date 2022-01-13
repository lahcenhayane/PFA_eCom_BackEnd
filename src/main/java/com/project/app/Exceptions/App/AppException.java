package com.project.app.Exceptions.App;

import org.springframework.http.HttpStatus;

public abstract class AppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AppException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public abstract HttpStatus getStatusCode();

}
