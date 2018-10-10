package com.aspiresys.employeemicroservice.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aspiresys.employeemicroservice.dto.EmployeeDTO;
import com.aspiresys.employeemicroservice.exception.EmployeeNotFoundException;
import com.aspiresys.employeemicroservice.exception.OperationCannotBeCompletedException;
import com.aspiresys.employeemicroservice.service.EmployeeService;

// TODO: Auto-generated Javadoc
/**
 * The Class EmployeeController.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	/** The employee service. */
	@Autowired
	EmployeeService employeeService;

	/**
	 * Adds the employee.
	 *
	 * @param employeeDTO the employee DTO
	 * @return the int
	 * @throws OperationCannotBeCompletedException the operation cannot be completed exception
	 */
	@PostMapping
	public int addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) throws OperationCannotBeCompletedException {
		return employeeService.addEmployee(employeeDTO);
	}

	/**
	 * Retrieve employees.
	 *
	 * @return the list
	 * @throws EmployeeNotFoundException the employee not found exception
	 */
	@GetMapping
	public List<EmployeeDTO> retrieveEmployees() throws EmployeeNotFoundException {
		return employeeService.retrieveEmployees();
	}

	/**
	 * Retrieve single employee.
	 *
	 * @param employeeId the employee id
	 * @return the employee DTO
	 * @throws EmployeeNotFoundException the employee not found exception
	 */
	@GetMapping("/{employeeId}")
	public EmployeeDTO retrieveSingleEmployee(@PathVariable("employeeId") Integer employeeId)
			throws EmployeeNotFoundException {
		return employeeService.retrieveSingleEmployee(employeeId);
	}

	/**
	 * Update employee.
	 *
	 * @param employeeId the employee id
	 * @param employeeDTO the employee DTO
	 * @return the int
	 * @throws OperationCannotBeCompletedException the operation cannot be completed exception
	 */
	@PutMapping("/{employeeId}")
	public int updateEmployee(@PathVariable("employeeId") Integer employeeId,
			@Valid @RequestBody EmployeeDTO employeeDTO) throws OperationCannotBeCompletedException {
		return employeeService.updateEmployee(employeeId, employeeDTO);
	}

	/**
	 * Delete employee.
	 *
	 * @param employeeId the employee id
	 * @return the int
	 * @throws OperationCannotBeCompletedException the operation cannot be completed exception
	 */
	@DeleteMapping("/{employeeId}")
	public int deleteEmployee(@PathVariable("employeeId") Integer employeeId) throws OperationCannotBeCompletedException {
		return employeeService.deleteEmployee(employeeId);
	}

	/**
	 * Retrieve single employee with leave.
	 *
	 * @param employeeId the employee id
	 * @return the employee with leave DTO
	 * @throws EmployeeNotFoundException the employee not found exception
	 */
	@GetMapping("/leave/{employeeId}")
	public EmployeeDTO retrieveSingleEmployeeWithLeave(@PathVariable("employeeId") Integer employeeId) throws EmployeeNotFoundException {
		return employeeService.retrieveSingleEmployeeWithLeave(employeeId);
//		return employeeService.getLeaveFromAPI(employeeId);
	}

}
