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
	@NotNull(message = "Name should not be null")
	@Size(min = 1, message = "Name should have atleast one character")
	@Pattern(regexp="([A-Za-z]*[ ])?[A-Za-z]{1,}")
	private String employeeName;
	
	/** The employee SL. */
	@NotNull(message = "Service Line should not be null")
	@Size(min = 1, message = "Service Line should have atleast one character")
	@Pattern(regexp="([A-Za-z]*[ ])?[A-Za-z]{1,}")
	private String employeeSL;
	
	/** The leave list. */
	private List<LeaveDTO> leaveList = new ArrayList<>();
	
	/** The employee project. */
	@NotNull(message = "Project should not be null")
	@Size(min = 1, message = "Project should have atleast one character")
	@Pattern(regexp="([A-Za-z]*[ ])?[A-Za-z]{1,}")
	private String employeeProject;
	
	/** The employee blood group. */
	@NotNull(message = "Blood group should not be null")
	@Size(min = 2, message = "Blood group should have atleast two characters")
	@Pattern(regexp="^(A|B|AB|O)[+-]$")
	private String employeeBloodGroup;


}
