
package com.project.common.exception;

import org.springframework.stereotype.Component;

@Component
public class DataCustomException extends CustomException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DataCustomException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DataCustomException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public DataCustomException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DataCustomException(Throwable cause) {
		super(cause);
	}
	
}
