package com.aspiresys.employeemicroservice.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class Employee.
 */
@Document(collection = "employee")

/**
 * Instantiates a new employee.
 */

/**
 * Instantiates a new employee.
 */
@Data // this annotation will generate getters and setters,@ToString using lambok
public class Employee {

	/** The employee id. */
	@Id
	@NotNull(message = "Id should not be null")
	@Size(min = 1, message = "Id should have atleast one character")
	private int employeeId;
	
	/** The employee name. */
	private String employeeName;
	
	/** The employee SL. */
	private String employeeSL;
	
	/** The employee project. */
	private String employeeProject;
	
	/** The employee blood group. */
	private String employeeBloodGroup;

}
