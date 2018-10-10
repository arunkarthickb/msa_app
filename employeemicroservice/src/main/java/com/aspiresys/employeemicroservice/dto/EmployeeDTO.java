package com.aspiresys.employeemicroservice.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * Gets the employee blood group.
 *
 * @return the employee blood group
 */

/**
 * Gets the employee blood group.
 *
 * @return the employee blood group
 */
@Getter

/**
 * Sets the employee blood group.
 *
 * @param employeeBloodGroup the new employee blood group
 */

/**
 * Sets the employee blood group.
 *
 * @param employeeBloodGroup the new employee blood group
 */
@Setter
public class EmployeeDTO {

	/** The employee id. */
	private Integer employeeId;
	
	/** The employee name. */
	private String employeeName;
	
	/** The employee SL. */
	private String employeeSL;
	
	/** The leave list. */
	private List<LeaveDTO> leaveList = new ArrayList<>();
	
	/** The employee project. */
	private String employeeProject;
	
	/** The employee blood group. */
	private String employeeBloodGroup;


}
