package com.employeeApplication.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmployeeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmployeeException(String msg) {
		super(msg);
	}

}
