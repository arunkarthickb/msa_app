package com.aspiresys.employeemicroservice.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.aspiresys.employeemicroservice.bean.Employee;

/**
 * The Interface EmployeeRepository.
 */
@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Integer> {



}
