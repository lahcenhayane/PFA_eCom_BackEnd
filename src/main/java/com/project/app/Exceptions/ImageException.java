package com.project.app.Exceptions;

import org.springframework.http.HttpStatus;

import com.project.app.Exceptions.App.AppException;

public class ImageException extends AppException {

	public ImageException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public HttpStatus getStatusCode() {
		// TODO Auto-generated method stub
		return HttpStatus.NOT_ACCEPTABLE;
	}

}
