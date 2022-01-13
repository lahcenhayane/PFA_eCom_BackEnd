package com.project.app.Exceptions.App;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

public class AppValidException {

	private List<String> errors;
	private HttpStatus httpStatus;
	private Date timetemps = new Date();

	public AppValidException() {
	}
	public AppValidException(List<String> errors, HttpStatus httpStatus) {
		this.errors = errors;
		this.httpStatus = httpStatus;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public Date getTimetemps() {
		return timetemps;
	}
	public void setTimetemps(Date timetemps) {
		this.timetemps = timetemps;
	}



}
