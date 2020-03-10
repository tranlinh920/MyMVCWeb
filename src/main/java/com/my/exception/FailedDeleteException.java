package com.my.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FailedDeleteException extends RuntimeException {

	public FailedDeleteException(String massage) {
		super(massage);
	}
}
