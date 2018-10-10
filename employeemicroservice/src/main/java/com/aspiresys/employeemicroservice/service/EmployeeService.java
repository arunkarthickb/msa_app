package com.aspiresys.employeemicroservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aspiresys.employeemicroservice.dto.EmployeeDTO;
import com.aspiresys.employeemicroservice.dto.LeaveDTO;
import com.aspiresys.employeemicroservice.exception.EmployeeNotFoundException;
import com.aspiresys.employeemicroservice.exception.OperationCannotBeCompletedException;

// TODO: Auto-generated Javadoc
/**
 * The Interface EmployeeService.
 */
@Service
public interface EmployeeService {

	/**
	 * Adds the employee.
	 *
	 * @param employee the employee
	 * @return the int
	 * @throws OperationCannotBeCompletedException the operation cannot be completed exception
	 */
	int addEmployee(EmployeeDTO employee) throws OperationCannotBeCompletedException;

	/**
	 * Retrieve employees.
	 *
	 * @return the list
	 * @throws EmployeeNotFoundException the employee not found exception
	 */
	List<EmployeeDTO> retrieveEmployees() throws EmployeeNotFoundException;

	/**
	 * Retrieve single employee.
	 *
	 * @param employeeId the employee id
	 * @return the employee DTO
	 * @throws EmployeeNotFoundException the employee not found exception
	 */
	EmployeeDTO retrieveSingleEmployee(int employeeId) throws EmployeeNotFoundException;

	/**
	 * Update employee.
	 *
	 * @param employeeId the employee id
	 * @param employeeDTO the employee DTO
	 * @return the int
	 * @throws OperationCannotBeCompletedException the operation cannot be completed exception
	 */
	int updateEmployee(Integer employeeId,EmployeeDTO employeeDTO) throws OperationCannotBeCompletedException;

	/**
	 * Delete employee.
	 *
	 * @param employeeId the employee id
	 * @return the int
	 * @throws OperationCannotBeCompletedException the operation cannot be completed exception
	 */
	int deleteEmployee(Integer employeeId) throws OperationCannotBeCompletedException;

	/**
	 * Retrieve single employee with leave.
	 *
	 * @param employeeId the employee id
	 * @return the employee with leave DTO
	 * @throws EmployeeNotFoundException the employee not found exception
	 */
	EmployeeDTO retrieveSingleEmployeeWithLeave(Integer employeeId) throws EmployeeNotFoundException;

	/**
	 * Gets the leave from API.
	 *
	 * @param employeeId the employee id
	 * @return the leave from API
	 */
	List<LeaveDTO> getLeaveFromAPI(Integer employeeId);

}
