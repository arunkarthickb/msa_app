package com.aspiresys.leavemicroservice.util;

import java.time.LocalDate;
import java.time.Period;

// TODO: Auto-generated Javadoc
/**
 * The Class DateValidator.
 */
public class DateValidator {

	/**
	 * Start date checker.
	 *
	 * @param startDate the start date
	 * @return true, if successful
	 */
	public boolean startDateChecker(LocalDate startDate) {

		LocalDate localDateToCheck = LocalDate.now();
		return localDateToCheck.isBefore(startDate);

	}

	/**
	 * End date and number of days checker.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param numberOfDays the number of days
	 * @return true, if successful
	 */
	public boolean endDateAndNumberOfDaysChecker(LocalDate startDate, LocalDate endDate, int numberOfDays) {

		Period intervalPeriod = Period.between(startDate, endDate);
		int differenceInDays = intervalPeriod.getDays();
		return (endDate.isAfter(startDate) || (numberOfDays <= differenceInDays));
	}

}
