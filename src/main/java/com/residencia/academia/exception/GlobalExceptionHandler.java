package com.residencia.academia.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoSuchElementFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleNoSuchElementFoundException(NoSuchElementFoundException itemNotFoundException,
			WebRequest request) {
		return buildErrorResponse(itemNotFoundException, HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(AtividadeNotFoundException.class)
	public ResponseEntity<String> handleNoInstrutorFoundException(AtividadeNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getErrMsg());
	}

	@ExceptionHandler(TurmaNotFoundException.class)
	public ResponseEntity<String> handleNoInstrutorFoundException(TurmaNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getErrMsg());
	}

	@ExceptionHandler(InstrutorNotFoundException.class)
	public ResponseEntity<String> handleNoInstrutorFoundException(InstrutorNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getErrMsg());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> handleAllUncaughtException(Exception exception, WebRequest request) {
		return buildErrorResponse(exception, "Ocorreu um erro desconhecido", HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	private ResponseEntity<Object> buildErrorResponse(Exception exception, HttpStatus httpStatus, WebRequest request) {
		return buildErrorResponse(exception, exception.getMessage(), httpStatus, request);
	}

	private ResponseEntity<Object> buildErrorResponse(Exception exception, String message, HttpStatus httpStatus,
			WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
		return ResponseEntity.status(httpStatus).body(errorResponse);
	}

	@Override
	public ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		return buildErrorResponse(ex, status, request);
	}
}
