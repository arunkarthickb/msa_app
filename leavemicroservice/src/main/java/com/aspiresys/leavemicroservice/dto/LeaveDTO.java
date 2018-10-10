package com.aspiresys.leavemicroservice.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * Gets the number of days.
 *
 * @return the number of days
 */
@Getter

/**
 * Sets the number of days.
 *
 * @param numberOfDays the new number of days
 */
@Setter
public class LeaveDTO {

	
	/** The leave id. */
	private int leaveId;
	
	/** The employee id. */
	@NotNull(message = "Id should not be null")
	@Size(min = 1, message = "Id should have atleast one integer")
	private int employeeId;
	
	/** The start date. */
	@NotNull(message = "Start Date should not be null")
	@Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$")
	private LocalDate startDate;
	
	/** The end date. */
	@NotNull(message = "End Date should not be null")
	@Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$")
	private LocalDate endDate;
	
	/** The number of days. */
	@NotNull(message = "Number Of Days should not be null")
	@Size(min = 1, message = "Number of days should have atleast one integer")
	private int numberOfDays;

}
