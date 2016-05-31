package com.prodigious.festivities.controller;

import java.util.List;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Handle exception with HttpStatus
 * 
 * @author Juan Joya
 *
 */
@ControllerAdvice
public class RESTExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralExceptiont(Exception ex) {
		ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
		bean.setBasename("ValidationMessages");
		return new ResponseEntity<String>(bean.getMessage(
				"festivity.exception.general.error", null,
				LocaleContextHolder.getLocale()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIlegalArgument(
			IllegalArgumentException ex) {
		ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
		bean.setBasename("ValidationMessages");
		return new ResponseEntity<String>(bean.getMessage(ex.getMessage(),
				null, LocaleContextHolder.getLocale()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> processValidationError(
			MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();

		return new ResponseEntity<String>(processFieldErrors(fieldErrors),
				HttpStatus.BAD_REQUEST);
	}

	private String processFieldErrors(List<FieldError> fieldErrors) {
		StringBuilder result = new StringBuilder();
		for (FieldError fieldError : fieldErrors) {
			result.append(fieldError.getDefaultMessage() + ", ");
		}

		return result.toString().substring(0, result.length() - 2);
	}

}
