package com.aspiresys.leavemicroservice.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aspiresys.leavemicroservice.exception.EmployeeNotFoundException;
import com.aspiresys.leavemicroservice.exception.LeaveNotAvailableException;

// TODO: Auto-generated Javadoc
/**
 * The Class ExceptionHelper.
 */
@ControllerAdvice
public class ExceptionHelper {

	/**
	 * Exception handler.
	 *
	 * @param exception the exception
	 * @param model the model
	 * @return the model
	 */
	@ExceptionHandler(value= { EmployeeNotFoundException.class,LeaveNotAvailableException.class })
	public Model exceptionHandler(Exception exception,Model model){
		model.addAttribute("ExceptionMessage", exception.getMessage());
		return model;
	}
	
}
