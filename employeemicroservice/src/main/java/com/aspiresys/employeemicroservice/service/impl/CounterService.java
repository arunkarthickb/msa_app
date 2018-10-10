package com.aspiresys.employeemicroservice.service.impl;

import com.aspiresys.employeemicroservice.bean.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

// TODO: Auto-generated Javadoc
/**
 * The Class CounterService.
 */
@Service
public class CounterService {

	/** The template. */
	@Autowired
	MongoTemplate template;

	/**
	 * Gets the next sequence.
	 *
	 * @param collection the collection
	 * @return the next sequence
	 */
	public int getNextSequence(String collection) {

		Counter counter = template.findAndModify(Query.query(Criteria.where("_id").is(collection)),
				new Update().inc("sequence", 1), FindAndModifyOptions.options().returnNew(true), Counter.class);

		return counter.getSequence();
	}

}
