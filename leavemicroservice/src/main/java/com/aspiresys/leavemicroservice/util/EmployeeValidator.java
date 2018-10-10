package com.aspiresys.leavemicroservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class EmployeeValidator.
 */
@Component
public class EmployeeValidator {

	/** The template. */
	@Autowired
	MongoTemplate template;

	/**
	 * Employee checker.
	 *
	 * @param employeeId the employee id
	 * @param collectionName the collection name
	 * @return true, if successful
	 */
	public boolean employeeChecker(int employeeId, String collectionName) {

		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(employeeId));

		return template.exists(query, collectionName);

	}

}
