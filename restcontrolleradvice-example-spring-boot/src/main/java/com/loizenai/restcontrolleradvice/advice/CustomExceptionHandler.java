package com.loizenai.restcontrolleradvice.advice;

import com.loizenai.restcontrolleradvice.exception.GenericException;
import com.loizenai.restcontrolleradvice.model.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ValidationException;
import java.util.Locale;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CustomExceptionHandler{

	private static final String HANDLING = "Handling";

	private final MessageSource messageSource;

	private final String GENERIC_ERROR = "GENERIC_ERROR";


	@ExceptionHandler({Exception.class})
	public ResponseEntity<ExceptionResponse> handleGenericException(final Exception exception, WebRequest request) {
		log.error(HANDLING, new GenericException(exception));
		final ResponseEntity<ExceptionResponse> response = this.getExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR,
				this.getDescription(GENERIC_ERROR),request);
		return response;
	}

	@ExceptionHandler(value = {ValidationException.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<ExceptionResponse> handleValidationException(ValidationException exception, WebRequest request) {

		final ResponseEntity<ExceptionResponse> response = this.getExceptionResponse(HttpStatus.BAD_REQUEST,
				this.getDescription(exception.getMessage()),request);
		return response;
	}

	private String getDescription(final String key) {
		return this.messageSource.getMessage(key, null, key, Locale.getDefault());
	}

	private ResponseEntity<ExceptionResponse> getExceptionResponse(final HttpStatus status,
																   final String message, WebRequest request) {
		if (status == null) {
			return this.getExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, message,request);
		}
		final ExceptionResponse response =
				ExceptionResponse.builder().status(status.value()).error(status.getReasonPhrase())
						.message(message).path(request.getDescription(false)).build();
		return new ResponseEntity<>(response, status);
	}

}