package com.project.app.Exceptions;

import org.springframework.http.HttpStatus;

import com.project.app.Exceptions.App.AppException;

public class NotFoundException extends AppException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(String msg) {
		super(msg);
	}

	@Override
	public HttpStatus getStatusCode() {
		return HttpStatus.NOT_FOUND;
	}

}
