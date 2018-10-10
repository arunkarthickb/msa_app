package com.aspiresys.employeemicroservice.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * Gets the number of days.
 *
 * @return the number of days
 */

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
	private int employeeId;

	/** The start date. */
	private LocalDate startDate;

	/** The end date. */
	private LocalDate endDate;

	/** The number of days. */
	private int numberOfDays;

}
