package com.aspiresys.employeemicroservice.service.impl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspiresys.employeemicroservice.LeaveMicroserviceProxy;
import com.aspiresys.employeemicroservice.bean.Employee;
import com.aspiresys.employeemicroservice.dto.EmployeeDTO;
import com.aspiresys.employeemicroservice.dto.LeaveDTO;
import com.aspiresys.employeemicroservice.exception.EmployeeNotFoundException;
import com.aspiresys.employeemicroservice.exception.OperationCannotBeCompletedException;
import com.aspiresys.employeemicroservice.repository.EmployeeRepository;
import com.aspiresys.employeemicroservice.service.EmployeeService;
import com.aspiresys.employeemicroservice.util.ApplicationConstants;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * The Class EmployeeServiceImpl.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	/**
	 * The employee repository.
	 */
	@Autowired
	EmployeeRepository employeeRepository;

	/**
	 * The counter service.
	 */
	@Autowired
	CounterService counterService;

	/** The proxy. */
	@Autowired
	LeaveMicroserviceProxy proxy;

	/**
	 * The model mapper.
	 */
	ModelMapper modelMapper = new ModelMapper();

	/**
	 * The logger.
	 */
	final Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aspiresys.employeemicroservice.service.EmployeeService#addEmployee(com.
	 * aspiresys.employeemicroservice.dto.EmployeeDTO)
	 */
	public int addEmployee(EmployeeDTO employeeDTO) throws OperationCannotBeCompletedException {

		employeeDTO.setEmployeeId(counterService.getNextSequence(ApplicationConstants.EMPLOYEE_COLLECTION_NAME));
		Employee employeeBean = modelMapper.map(employeeDTO, Employee.class);

		logger.debug(ApplicationConstants.SAVE_EMPLOYEE_DEBUG + employeeDTO.getEmployeeId());
		Employee employeeCheck = employeeRepository.save(employeeBean);

		if (employeeCheck != null) {
			logger.info(ApplicationConstants.SAVE_EMPLOYEE_INFO);
			return employeeDTO.getEmployeeId();

		} else {
			logger.error(ApplicationConstants.SAVE_EMPLOYEE_ERROR);
			throw new OperationCannotBeCompletedException(ApplicationConstants.UNABLE_MESSAGE);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aspiresys.employeemicroservice.service.EmployeeService#retrieveEmployees(
	 * )
	 */
	@Override
	public List<EmployeeDTO> retrieveEmployees() throws EmployeeNotFoundException {

		logger.debug(ApplicationConstants.RETRIEVE_EMPLOYEE_DEBUG);
		List<Employee> employeeCheck = employeeRepository.findAll();

		if (employeeCheck != null) {
			Type targetListType = new TypeToken<List<EmployeeDTO>>() {
			}.getType();
			List<EmployeeDTO> employeeDTO = modelMapper.map(employeeCheck, targetListType);
			logger.info(ApplicationConstants.RETRIEVE_EMPLOYEE_INFO);
			return employeeDTO;
		} else {
			logger.error(ApplicationConstants.RETRIEVE_EMPLOYEE_ERROR);
			throw new EmployeeNotFoundException(ApplicationConstants.EMPLOYEE_NOT_FOUND_MESSAGE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aspiresys.employeemicroservice.service.EmployeeService#
	 * retrieveSingleEmployee(int)
	 */
	@Override
	public EmployeeDTO retrieveSingleEmployee(int employeeId) throws EmployeeNotFoundException {

		logger.debug(ApplicationConstants.RETRIEVE_EMPLOYEE_DEBUG);
		Optional<Employee> employeeCheck = employeeRepository.findById(employeeId);

		if (employeeCheck.isPresent()) {
			EmployeeDTO employeeDTO = modelMapper.map(employeeCheck.get(), EmployeeDTO.class);
			logger.info(ApplicationConstants.RETRIEVE_EMPLOYEE_INFO);
			return employeeDTO;
		} else {
			logger.error(ApplicationConstants.RETRIEVE_EMPLOYEE_ERROR);
			throw new EmployeeNotFoundException(ApplicationConstants.EMPLOYEE_NOT_FOUND_MESSAGE);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aspiresys.employeemicroservice.service.EmployeeService#updateEmployee(
	 * java.lang.Integer, com.aspiresys.employeemicroservice.dto.EmployeeDTO)
	 */
	@Override
	public int updateEmployee(Integer employeeId, EmployeeDTO employeeDTO) throws OperationCannotBeCompletedException {

		logger.debug(ApplicationConstants.UPDATE_EMPLOYEE_DEBUG);
		Optional<Employee> employeeCheck = employeeRepository.findById(employeeId);

		if (employeeCheck.isPresent()) {
			employeeCheck.get().setEmployeeName(employeeDTO.getEmployeeName());
			employeeCheck.get().setEmployeeSL(employeeDTO.getEmployeeSL());
			employeeRepository.save(employeeCheck.get());
			EmployeeDTO employeeDTOReturn = modelMapper.map(employeeCheck.get(), EmployeeDTO.class);
			logger.info(ApplicationConstants.UPDATE_EMPLOYEE_INFO);
			return employeeDTOReturn.getEmployeeId();
		} else {
			logger.error(ApplicationConstants.UPDATE_EMPLOYEE_ERROR);
			throw new OperationCannotBeCompletedException(ApplicationConstants.UNABLE_MESSAGE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aspiresys.employeemicroservice.service.EmployeeService#deleteEmployee(
	 * java.lang.Integer)
	 */
	@Override
	public int deleteEmployee(Integer employeeId) throws OperationCannotBeCompletedException {

		logger.debug(ApplicationConstants.DELETE_EMPLOYEE_DEBUG);
		Optional<Employee> employeeCheck = employeeRepository.findById(employeeId);

		if (employeeCheck.isPresent()) {
			employeeId = proxy.cancelLeave(employeeId);
			employeeRepository.deleteById(employeeId);
			logger.info(ApplicationConstants.DELETE_EMPLOYEE_DEBUG);
			return employeeId;
		} else {
			logger.error(ApplicationConstants.DELETE_EMPLOYEE_ERROR);
			throw new OperationCannotBeCompletedException(ApplicationConstants.UNABLE_MESSAGE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aspiresys.employeemicroservice.service.EmployeeService#
	 * retrieveSingleEmployeeWithLeave(java.lang.Integer)
	 */

	@Override
	public EmployeeDTO retrieveSingleEmployeeWithLeave(Integer employeeId) throws EmployeeNotFoundException {

		logger.debug(ApplicationConstants.RETRIEVE_EMPLOYEE_WITH_LEAVE_DEBUG);
		Optional<Employee> employeeCheck = employeeRepository.findById(employeeId);

		if (employeeCheck.isPresent()) {
			EmployeeDTO employeeDTOReturn = modelMapper.map(employeeCheck.get(), EmployeeDTO.class);

			List<LeaveDTO> leaveList = getLeaveFromAPI(employeeId);

			employeeDTOReturn.setLeaveList(leaveList);

			logger.info(ApplicationConstants.RETRIEVE_EMPLOYEE_WITH_API_DEBUG);
			return employeeDTOReturn;
		} else {
			logger.error(ApplicationConstants.RETRIEVE_EMPLOYEE_WITH_LEAVE_ERROR);
			throw new EmployeeNotFoundException(ApplicationConstants.EMPLOYEE_NOT_FOUND_MESSAGE);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aspiresys.employeemicroservice.service.EmployeeService#getLeaveFromAPI(
	 * java.lang.Integer)
	 */
	@HystrixCommand(fallbackMethod = "serviceFailAlternate")
	@Override
	public List<LeaveDTO> getLeaveFromAPI(Integer employeeId) {
		logger.debug(ApplicationConstants.RETRIEVE_EMPLOYEE_WITH_API_DEBUG);
		List<LeaveDTO> leaveList = proxy.retrieveLeave(employeeId);
		return leaveList;
	}

	/**
	 * Service fail alternate.
	 *
	 * @param employeeId
	 *            the employee id
	 * @return the list
	 * @throws EmployeeNotFoundException
	 *             the employee not found exception
	 */
	List<LeaveDTO> serviceFailAlternate(Integer employeeId) throws EmployeeNotFoundException {
		return null;
	}

}