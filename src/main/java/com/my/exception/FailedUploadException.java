package com.my.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FailedUploadException extends RuntimeException {
	public FailedUploadException(String exception) {
		super(exception);
	}
}
