package com.aspiresys.leavemicroservice.util;

// TODO: Auto-generated Javadoc
/**
 * The Class ApplicationConstants.
 */
public class ApplicationConstants {

	/** The Constant EMPLOYEE_NOT_FOUND_MESSAGE. */
	public static final String EMPLOYEE_NOT_FOUND_MESSAGE="The Requested employee is not found";
	
	/** The Constant UNABLE_MESSAGE. */
	public static final String UNABLE_MESSAGE="The Requested cannot be completed";
	
	/** The Constant EMPLOYEE_COLLECTION_NAME. */
	public static final String EMPLOYEE_COLLECTION_NAME="employee";
	
	/** The Constant START_DATE_ERROR. */
	public static final String START_DATE_ERROR = "Entered StartDate is Invalid";
	
	/** The Constant END_DATE_ERROR. */
	public static final String END_DATE_ERROR = "End date should be valid and greater than start date";
	
	/** The Constant LEAVE_COLLECTION_NAME. */
	public static final String LEAVE_COLLECTION_NAME = "leave";
	
	/** The Constant UNABLE_TO_ADD_MESSAGE. */
	public static final String UNABLE_TO_ADD_MESSAGE = "Unable to add leave to the employee";
	
	/** The Constant LEAVE_NOT_AVAILABLE_MESSAGE. */
	public static final String LEAVE_NOT_AVAILABLE_MESSAGE = "Leave not present for the given id";
	
	
	//log mesaages
	public static final String ADD_LEAVE_DEBUG="Adding leave for EmployeeID: ";
	public static final String ADD_LEAVE_INFO="Leave Added successfully";
	public static final String ADD_LEAVE_ERROR="Cannot add leave";
	
	public static final String RETRIEVE_LEAVE_DEBUG="Retrieving leave Details for EmployeeID: ";
	public static final String RETRIEVE_LEAVE_INFO="Retrieving leave Details for EmployeeID: ";
	public static final String RETRIEVE_LEAVE_ERROR="Cannot retrieve leavedetails";
	
	public static final String UPDATE_LEAVE_DEBUG="Updating leave Details for EmployeeID:";
	public static final String UPDATE_LEAVE_INFO="Leave details updated successfully";
	public static final String UPDATE_LEAVE_ERROR="Error in updating leave";
	
	public static final String CANCEL_LEAVE_DEBUG="Cancelling leave Details for EmployeeID: " ;
	public static final String CANCEL_LEAVE_INFO="Leave cancelled successfully";
	public static final String CANCEL_LEAVE_ERROR="Leave couldn't be cancelled";

	
	
}
