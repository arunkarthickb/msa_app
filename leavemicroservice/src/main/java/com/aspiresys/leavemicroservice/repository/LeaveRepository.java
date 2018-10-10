package com.aspiresys.leavemicroservice.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.aspiresys.leavemicroservice.bean.Leave;

// TODO: Auto-generated Javadoc
/**
 * The Interface LeaveRepository.
 */
@Repository
public interface LeaveRepository extends MongoRepository<Leave, Integer> {

	/**
	 * Exists by employee id.
	 *
	 * @param employeeId the employee id
	 * @return the list
	 */
	@Query("{ 'employeeId': ?0}")
	public List<Leave> existsByEmployeeId(int employeeId);
	
	

}
