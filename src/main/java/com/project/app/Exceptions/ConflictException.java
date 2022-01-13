package com.project.app.Exceptions;

import org.springframework.http.HttpStatus;

import com.project.app.Exceptions.App.AppException;

public class ConflictException extends AppException {

	public ConflictException(String message) {
		super(message);
	}

	@Override
	public HttpStatus getStatusCode() {
		// TODO Auto-generated method stub
		return HttpStatus.CONFLICT;
	}



}
