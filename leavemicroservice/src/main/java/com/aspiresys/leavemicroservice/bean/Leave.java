package com.aspiresys.leavemicroservice.bean;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class Leave.
 */
@Document(collection = "leave")

/**
 * Instantiates a new leave.
 */
@Data // this annotation will generate getters and setters,@ToString using lambok
public class Leave {

	/** The leave id. */
	@Id
	@NotNull(message = "Id should not be null")
	@Size(min = 1, message = "Id should have atleast one character")
	private int leaveId;
	
	/** The employee id. */
	private int employeeId;
	
	/** The start date. */
	private LocalDate startDate;
	
	/** The end date. */
	private LocalDate endDate;
	
	/** The numberof days. */
	private int numberofDays;

}
