package com.aspiresys.leavemicroservice.exception;

/**
 * The Class EmployeeNotFoundException.
 */
public class EmployeeNotFoundException extends Exception {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
 	 * Instantiates a new employee not found exception.
 	 *
 	 * @param string the string
 	 */
 	public EmployeeNotFoundException(String string) {
	        super(string);

	 }


}
