package com.project.app.Exceptions;

import org.springframework.http.HttpStatus;

import com.project.app.Exceptions.App.AppException;

public class AuthException extends AppException {

	private static final long serialVersionUID = 1L;

	public AuthException(String msg) {
		super(msg);
	}

	@Override
	public HttpStatus getStatusCode() {
		// TODO Auto-generated method stub
		return HttpStatus.UNAUTHORIZED;
	}


}
