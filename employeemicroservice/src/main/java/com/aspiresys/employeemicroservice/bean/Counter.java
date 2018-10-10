package com.aspiresys.employeemicroservice.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * The Class Counter.
 */
@Document

/**
 * Gets the sequence.
 *
 * @return the sequence
 */

/**
 * Gets the sequence.
 *
 * @return the sequence
 */
@Getter 
 /**
  * Sets the sequence.
  *
  * @param sequence the new sequence
  */
 
 /**
  * Sets the sequence.
  *
  * @param sequence the new sequence
  */
 @Setter
public class Counter {

	/** The counter id. */
	@Id
	private String counterId;
	
	/** The sequence. */
	private int sequence;

}
