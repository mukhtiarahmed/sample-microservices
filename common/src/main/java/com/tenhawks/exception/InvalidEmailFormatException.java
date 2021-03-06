/*
 * Copyright (C) 2016 Ten Hawks, All Rights Reserved.
 */
package com.tenhawks.exception;
/**
 * This exception is thrown when an invalid email format provide.
 * 
 * @author Mukhtiar Ahmed
 *
 * version 1.0
 */
public class InvalidEmailFormatException extends SystemException {

	/**
	 *  The serial version id
	 */
	private static final long serialVersionUID = -1223704093843950795L;

	/**
	 * <p>
	 * This is the constructor of <code>InvalidEmailFormatException</code> class with message and cause arguments.
	 * </p>
	 *
	 * @param message the error message.
	 * @param exception the cause of the exception.
	 */
	public InvalidEmailFormatException(String message, Throwable exception) {
		super(message, exception);

	}

	/**
	 * <p>
	 * This is the constructor of <code>InvalidEmailFormatException</code> class with message and cause arguments.
	 * </p>
	 *
	 * @param message the error message.     
	 */
	public InvalidEmailFormatException(String message) {
		super(message);

	}


}
