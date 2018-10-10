package com.aspiresys.employeemicroservice;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.aspiresys.employeemicroservice.dto.LeaveDTO;


// TODO: Auto-generated Javadoc
/**
 * The Interface LeaveMicroserviceProxy.
 */
@FeignClient(name="leave-service")
@RibbonClient(name="leave-service")
public interface LeaveMicroserviceProxy {

	/**
	 * Retrieve leave.
	 *
	 * @param employeeId the employee id
	 * @return the list
	 */
	@GetMapping("/employee/{employeeId}/leave")
	  public List<LeaveDTO> retrieveLeave
	    (@PathVariable("employeeId") int employeeId);
	
	/**
	 * Cancel leave.
	 *
	 * @param employeeId the employee id
	 * @return the int
	 */
	@DeleteMapping("/employee/{employeeId}/leave")
		public int cancelLeave
		(@PathVariable("employeeId") int employeeId);
}
