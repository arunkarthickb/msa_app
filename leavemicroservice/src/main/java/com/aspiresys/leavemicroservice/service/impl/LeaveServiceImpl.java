package com.aspiresys.leavemicroservice.service.impl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspiresys.leavemicroservice.bean.Leave;
import com.aspiresys.leavemicroservice.dto.LeaveDTO;
import com.aspiresys.leavemicroservice.exception.EmployeeNotFoundException;
import com.aspiresys.leavemicroservice.exception.InvalidDateException;
import com.aspiresys.leavemicroservice.exception.LeaveNotAvailableException;
import com.aspiresys.leavemicroservice.exception.OperationCannotBeCompletedException;
import com.aspiresys.leavemicroservice.repository.LeaveRepository;
import com.aspiresys.leavemicroservice.service.LeaveService;
import com.aspiresys.leavemicroservice.util.DateValidator;
import com.aspiresys.leavemicroservice.util.EmployeeValidator;
import com.aspiresys.leavemicroservice.util.ApplicationConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class LeaveServiceImpl.
 */
@Service
public class LeaveServiceImpl implements LeaveService {

	/** The leave repository. */
	@Autowired
	LeaveRepository leaveRepository;

	/** The leave service. */
	@Autowired
	LeaveService leaveService;

	/** The counter service. */
	@Autowired
	CounterService counterService;

	/** The employee validator. */
	@Autowired
	EmployeeValidator employeeValidator;

	/** The date validator. */
	DateValidator dateValidator = new DateValidator();

	/** The model mapper. */
	ModelMapper modelMapper = new ModelMapper();

	/** The logger. */
	final Logger logger = Logger.getLogger(LeaveServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aspiresys.leavemicroservice.service.LeaveService#addLeave(int,
	 * com.aspiresys.leavemicroservice.dto.LeaveDTO)
	 */
	@Override
	public int addLeave(int employeeId, LeaveDTO leaveDTO)
			throws EmployeeNotFoundException, InvalidDateException, OperationCannotBeCompletedException {

		if (!employeeValidator.employeeChecker(employeeId, ApplicationConstants.EMPLOYEE_COLLECTION_NAME))
			throw new EmployeeNotFoundException(ApplicationConstants.EMPLOYEE_NOT_FOUND_MESSAGE);
		if (!dateValidator.startDateChecker(leaveDTO.getStartDate()))
			throw new InvalidDateException(ApplicationConstants.START_DATE_ERROR);

		if (!dateValidator.endDateAndNumberOfDaysChecker(leaveDTO.getStartDate(), leaveDTO.getEndDate(),
				leaveDTO.getNumberOfDays()))
			throw new InvalidDateException(ApplicationConstants.END_DATE_ERROR);

		leaveDTO.setLeaveId(counterService.getNextSequence(ApplicationConstants.LEAVE_COLLECTION_NAME));
		leaveDTO.setEmployeeId(employeeId);
		Leave leaveBean = modelMapper.map(leaveDTO, Leave.class);

		logger.debug(ApplicationConstants.ADD_LEAVE_DEBUG + leaveDTO.getEmployeeId());
		leaveBean = leaveRepository.save(leaveBean);

		if (leaveBean != null) {
			leaveDTO = modelMapper.map(leaveBean, LeaveDTO.class);
			logger.info(ApplicationConstants.ADD_LEAVE_INFO);
			return leaveDTO.getLeaveId();
		} else {
			logger.error(ApplicationConstants.ADD_LEAVE_ERROR);
			throw new OperationCannotBeCompletedException(ApplicationConstants.UNABLE_TO_ADD_MESSAGE);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aspiresys.leavemicroservice.service.LeaveService#getLeavedetails(int)
	 */
	@Override
	public List<LeaveDTO> getLeavedetails(int employeeId) throws LeaveNotAvailableException, EmployeeNotFoundException {

		if (!employeeValidator.employeeChecker(employeeId, ApplicationConstants.EMPLOYEE_COLLECTION_NAME))
			throw new EmployeeNotFoundException(ApplicationConstants.EMPLOYEE_NOT_FOUND_MESSAGE);

		logger.debug(ApplicationConstants.RETRIEVE_LEAVE_DEBUG + employeeId);
		List<Leave> leave = leaveRepository.existsByEmployeeId(employeeId);

		if (leave.isEmpty()) {
			logger.error(ApplicationConstants.RETRIEVE_LEAVE_ERROR);
			throw new LeaveNotAvailableException(ApplicationConstants.LEAVE_NOT_AVAILABLE_MESSAGE);
		} else {
			logger.info(ApplicationConstants.RETRIEVE_LEAVE_INFO);
			Type targetListType = new TypeToken<List<LeaveDTO>>() {
			}.getType();
			return modelMapper.map(leave, targetListType);

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aspiresys.leavemicroservice.service.LeaveService#getSingleLeavedetails(
	 * int, int)
	 */
	@Override
	public LeaveDTO getSingleLeavedetails(int employeeId, int leaveId)
			throws OperationCannotBeCompletedException, EmployeeNotFoundException {

		if (!employeeValidator.employeeChecker(employeeId, ApplicationConstants.EMPLOYEE_COLLECTION_NAME))
			throw new EmployeeNotFoundException(ApplicationConstants.EMPLOYEE_NOT_FOUND_MESSAGE);

		logger.debug(ApplicationConstants.RETRIEVE_LEAVE_DEBUG + employeeId);
		Optional<Leave> leaveBean = leaveRepository.findById(leaveId);

		if (leaveBean.isPresent()) {
			logger.info(ApplicationConstants.RETRIEVE_LEAVE_INFO);
			return modelMapper.map(leaveBean.get(), LeaveDTO.class);
		} else {
			logger.error(ApplicationConstants.RETRIEVE_LEAVE_ERROR);
			throw new OperationCannotBeCompletedException(ApplicationConstants.UNABLE_MESSAGE);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aspiresys.leavemicroservice.service.LeaveService#updateLeave(int,
	 * int, com.aspiresys.leavemicroservice.dto.LeaveDTO)
	 */
	@Override
	public int updateLeave(int employeeId, int leaveId, LeaveDTO leaveDTO) throws EmployeeNotFoundException,
			LeaveNotAvailableException, InvalidDateException, OperationCannotBeCompletedException {

		if (!employeeValidator.employeeChecker(employeeId, ApplicationConstants.EMPLOYEE_COLLECTION_NAME))
			throw new EmployeeNotFoundException(ApplicationConstants.EMPLOYEE_NOT_FOUND_MESSAGE);

		if (!dateValidator.startDateChecker(leaveDTO.getStartDate()))
			throw new InvalidDateException(ApplicationConstants.START_DATE_ERROR);

		if (!dateValidator.endDateAndNumberOfDaysChecker(leaveDTO.getStartDate(), leaveDTO.getEndDate(),
				leaveDTO.getNumberOfDays()))
			throw new InvalidDateException(ApplicationConstants.END_DATE_ERROR);

		logger.debug(ApplicationConstants.UPDATE_LEAVE_DEBUG + employeeId);
		Optional<Leave> leaveBeanFromDB = leaveRepository.findById(leaveId);

		if (leaveBeanFromDB.isPresent()) {
			leaveBeanFromDB.get().setStartDate(leaveDTO.getStartDate());
			leaveBeanFromDB.get().setEndDate(leaveDTO.getEndDate());
			leaveBeanFromDB.get().setNumberofDays(leaveDTO.getNumberOfDays());

			leaveRepository.save(leaveBeanFromDB.get());

			logger.info(ApplicationConstants.UPDATE_LEAVE_INFO);
			leaveDTO = modelMapper.map(leaveBeanFromDB.get(), LeaveDTO.class);
			return leaveDTO.getLeaveId();
		} else {
			logger.error(ApplicationConstants.UPDATE_LEAVE_ERROR);
			throw new OperationCannotBeCompletedException(ApplicationConstants.UNABLE_MESSAGE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aspiresys.leavemicroservice.service.LeaveService#cancelLeave(int,
	 * int)
	 */
	@Override
	public int cancelLeave(int employeeId, int leaveId)
			throws EmployeeNotFoundException, InvalidDateException, LeaveNotAvailableException {

		if (!employeeValidator.employeeChecker(employeeId, ApplicationConstants.EMPLOYEE_COLLECTION_NAME))
			throw new EmployeeNotFoundException(ApplicationConstants.EMPLOYEE_NOT_FOUND_MESSAGE);

		logger.debug(ApplicationConstants.CANCEL_LEAVE_DEBUG + employeeId);
		Optional<Leave> leaveBeanFromDB = leaveRepository.findById(leaveId);

		if (leaveBeanFromDB.isPresent()) {
			leaveRepository.deleteById(leaveId);
			logger.info(ApplicationConstants.CANCEL_LEAVE_INFO);
			return leaveBeanFromDB.get().getLeaveId();
		} else {
			logger.error(ApplicationConstants.CANCEL_LEAVE_ERROR);
			throw new LeaveNotAvailableException(ApplicationConstants.LEAVE_NOT_AVAILABLE_MESSAGE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aspiresys.leavemicroservice.service.LeaveService#cancelAllLeave(int)
	 */
	@Override
	public int cancelAllLeave(int employeeId) throws EmployeeNotFoundException, LeaveNotAvailableException {
		if (!employeeValidator.employeeChecker(employeeId, ApplicationConstants.EMPLOYEE_COLLECTION_NAME))
			throw new EmployeeNotFoundException(ApplicationConstants.EMPLOYEE_NOT_FOUND_MESSAGE);

		logger.debug(ApplicationConstants.CANCEL_LEAVE_DEBUG + employeeId);
		List<Leave> leaveList = leaveRepository.existsByEmployeeId(employeeId);

		if (leaveList != null) {
			leaveList.forEach(list -> leaveRepository.deleteById(list.getLeaveId()));
			logger.info(ApplicationConstants.CANCEL_LEAVE_INFO);
			return employeeId;
		} else {
			logger.error(ApplicationConstants.CANCEL_LEAVE_ERROR);
			throw new LeaveNotAvailableException(ApplicationConstants.LEAVE_NOT_AVAILABLE_MESSAGE);

		}
	}

}
