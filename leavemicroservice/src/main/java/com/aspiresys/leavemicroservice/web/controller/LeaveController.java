package com.aspiresys.leavemicroservice.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aspiresys.leavemicroservice.dto.LeaveDTO;
import com.aspiresys.leavemicroservice.exception.EmployeeNotFoundException;
import com.aspiresys.leavemicroservice.exception.InvalidDateException;
import com.aspiresys.leavemicroservice.exception.LeaveNotAvailableException;
import com.aspiresys.leavemicroservice.exception.OperationCannotBeCompletedException;
import com.aspiresys.leavemicroservice.service.LeaveService;

// TODO: Auto-generated Javadoc
/**
 * The Class LeaveController.
 */
@RestController
@RequestMapping("/employee/{employeeId}/leave")
public class LeaveController {
	
	/** The leaveservice. */
	@Autowired
	LeaveService leaveservice;

	/**
	 * Adds the leave.
	 *
	 * @param employeeId the employee id
	 * @param leave the leave
	 * @throws EmployeeNotFoundException the employee not found exception
	 * @throws InvalidDateException the invalid date exception
	 * @throws OperationCannotBeCompletedException the operation cannot be completed exception
	 */
	@PostMapping
	public void addLeave(@PathVariable("employeeId") int employeeId, @RequestBody LeaveDTO leave)
			throws EmployeeNotFoundException, InvalidDateException, OperationCannotBeCompletedException {
		leaveservice.addLeave(employeeId, leave);
	}

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
	@PutMapping("/{leaveId}")
	public int updateLeave(@PathVariable("employeeId") int employeeId, @PathVariable("leaveId") int leaveId,
			@RequestBody LeaveDTO leaveDTO) throws EmployeeNotFoundException, InvalidDateException,
			LeaveNotAvailableException, OperationCannotBeCompletedException {
		return leaveservice.updateLeave(employeeId, leaveId, leaveDTO);
	}

	/**
	 * Gets the leave details.
	 *
	 * @param employeeId the employee id
	 * @return the leave details
	 * @throws LeaveNotAvailableException the leave not available exception
	 * @throws EmployeeNotFoundException the employee not found exception
	 */
	@GetMapping
	public List<LeaveDTO> getLeaveDetails(@PathVariable("employeeId") int employeeId)
			throws LeaveNotAvailableException, EmployeeNotFoundException {
		return leaveservice.getLeavedetails(employeeId);
	}

	/**
	 * Gets the single leave details.
	 *
	 * @param employeeId the employee id
	 * @param leaveId the leave id
	 * @return the single leave details
	 * @throws LeaveNotAvailableException the leave not available exception
	 * @throws EmployeeNotFoundException the employee not found exception
	 * @throws OperationCannotBeCompletedException the operation cannot be completed exception
	 */
	@GetMapping("/{leaveId}")
	public LeaveDTO getSingleLeaveDetails(@PathVariable("employeeId") int employeeId,
			@PathVariable("leaveId") int leaveId)
			throws LeaveNotAvailableException, EmployeeNotFoundException, OperationCannotBeCompletedException {
		return leaveservice.getSingleLeavedetails(employeeId, leaveId);
	}

	/**
	 * Cancel leave.
	 *
	 * @param employeeId the employee id
	 * @param leaveId the leave id
	 * @return the int
	 * @throws EmployeeNotFoundException the employee not found exception
	 * @throws InvalidDateException the invalid date exception
	 * @throws LeaveNotAvailableException the leave not available exception
	 */
	@DeleteMapping("/{leaveId}")
	public int cancelLeave(@PathVariable("empoyeeId") int employeeId,@PathVariable("leaveId") int leaveId)
			throws EmployeeNotFoundException, InvalidDateException, LeaveNotAvailableException {
		return leaveservice.cancelLeave(employeeId,leaveId);
	}
	
	/**
	 * Cancel all leave.
	 *
	 * @param employeeId the employee id
	 * @return the int
	 * @throws EmployeeNotFoundException the employee not found exception
	 * @throws LeaveNotAvailableException the leave not available exception
	 */
	@DeleteMapping
	public int cancelAllLeave(@PathVariable("employeeId") int employeeId) throws EmployeeNotFoundException, LeaveNotAvailableException {
		return leaveservice.cancelAllLeave(employeeId);
		
	}

}
