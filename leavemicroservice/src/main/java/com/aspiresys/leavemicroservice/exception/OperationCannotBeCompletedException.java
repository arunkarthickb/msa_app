package com.aspiresys.leavemicroservice.exception;

/**
 * The Class OperationCannotBeCompletedException.
 */
public class OperationCannotBeCompletedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new operation cannot be completed exception.
	 *
	 * @param message the message
	 */
	public OperationCannotBeCompletedException(String message) {
		super(message);
	}
}
