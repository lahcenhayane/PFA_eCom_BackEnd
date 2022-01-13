package com.project.app.Exceptions.App;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class AppDetailsException {
	private String message;
	private String url;
	private HttpStatus httpStatus;
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss ")
	private Date timestamp = new Date();

	public AppDetailsException() {
		super();
	}

	public AppDetailsException(String message) {
		this.message = message;
	}

	public AppDetailsException(String message, String url, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.url = url;
		this.httpStatus = httpStatus;
	}

	public AppDetailsException(String message, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}


}
