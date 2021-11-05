
package com.project.common.exception;

import org.springframework.stereotype.Component;

@Component
public class BusinessException extends CustomException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3701412308874878843L;

	/**
	 * 
	 */
	
	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

}
