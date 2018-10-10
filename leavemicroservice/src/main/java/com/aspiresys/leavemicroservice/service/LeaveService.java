package com.aspiresys.leavemicroservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aspiresys.leavemicroservice.dto.LeaveDTO;
import com.aspiresys.leavemicroservice.exception.EmployeeNotFoundException;
import com.aspiresys.leavemicroservice.exception.InvalidDateException;
import com.aspiresys.leavemicroservice.exception.LeaveNotAvailableException;
import com.aspiresys.leavemicroservice.exception.OperationCannotBeCompletedException;

// TODO: Auto-generated Javadoc
/**
 * The Interface LeaveService.
 */
@Service
public interface LeaveService {

	/**
	 * Adds the leave.
	 *
	 * @param employeeId the employee id
	 * @param leaveDTO the leave DTO
	 * @return the int
	 * @throws EmployeeNotFoundException the employee not found exception
	 * @throws InvalidDateException the invalid date exception
	 * @throws OperationCannotBeCompletedException the operation cannot be completed exception
	 */
	int addLeave(int employeeId, LeaveDTO leaveDTO) throws EmployeeNotFoundException, InvalidDateException, OperationCannotBeCompletedException;

	/**
	 * Gets the single leavedetails.
	 *
	 * @param employeeId the employee id
	 * @param leaveId the leave id
	 * @return the single leavedetails
	 * @throws OperationCannotBeCompletedException the operation cannot be completed exception
	 * @throws EmployeeNotFoundException the employee not found exception
	 */
	LeaveDTO getSingleLeavedetails(int employeeId,int leaveId) throws OperationCannotBeCompletedException, EmployeeNotFoundException;

	/**
	 * Gets the leavedetails.
	 *
	 * @param employeeId the employee id
	 * @return the leavedetails
	 * @throws EmployeeNotFoundException the employee not found exception
	 * @throws LeaveNotAvailableException the leave not available exception
	 */
	List<LeaveDTO> getLeavedetails(int employeeId) throws EmployeeNotFoundException, LeaveNotAvailableException;

	/**
	 * Update leave.
	 *
	 * @param employeeId the employee id
	 * @param leaveId the leave id
	 * @param leaveDTO the leave DTO
	 * @return the int
	 * @throws EmployeeNotFoundException the employee not found exception
	 * @throws InvalidDateException the invalid date exception
	 * @throws LeaveNotAvailableException the leave not available exception
	 * @throws OperationCannotBeCompletedException the operation cannot be completed exception
	 */
	int updateLeave(int employeeId, int leaveId,LeaveDTO leaveDTO)
			throws EmployeeNotFoundException, InvalidDateException, LeaveNotAvailableException, OperationCannotBeCompletedException;

	/**
	 * Cancel leave.
	 *
	 * @param employeeid the employeeid
	 * @param leaveId the leave id
	 * @return the int
	 * @throws EmployeeNotFoundException the employee not found exception
	 * @throws InvalidDateException the invalid date exception
	 * @throws LeaveNotAvailableException the leave not available exception
	 */
	int cancelLeave(int employeeid,int leaveId)
			throws EmployeeNotFoundException, InvalidDateException, LeaveNotAvailableException;

	/**
	 * Cancel all leave.
	 *
	 * @param employeeId the employee id
	 * @return the int
	 * @throws EmployeeNotFoundException the employee not found exception
	 * @throws LeaveNotAvailableException the leave not available exception
	 */
	int cancelAllLeave(int employeeId) throws EmployeeNotFoundException, LeaveNotAvailableException;

}
