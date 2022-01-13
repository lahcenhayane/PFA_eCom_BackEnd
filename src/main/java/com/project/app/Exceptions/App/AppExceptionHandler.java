package com.project.app.Exceptions.App;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler{


	@ExceptionHandler(AppException.class)
	public ResponseEntity<AppDetailsException> handleAppException(AppException ex, WebRequest web){
		AppDetailsException errorException = new AppDetailsException(ex.getMessage(), web.getDescription(false),ex.getStatusCode());
		return new ResponseEntity<>(errorException, ex.getStatusCode());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		AppValidException validException = new AppValidException();
		validException.setHttpStatus(HttpStatus.BAD_REQUEST);

		List<FieldError> list_errors = ex.getBindingResult().getFieldErrors();
		list_errors.stream().map(row->validException.getErrors().add(row.getDefaultMessage())).collect(Collectors.toList());
		return new ResponseEntity<>(list_errors, validException.getHttpStatus());
	}

}
