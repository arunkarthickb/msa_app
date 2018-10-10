package com.aspiresys.employeemicroservice;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.aspiresys.employeemicroservice.bean.Employee;
import com.aspiresys.employeemicroservice.dto.EmployeeDTO;
import com.aspiresys.employeemicroservice.exception.EmployeeNotFoundException;
import com.aspiresys.employeemicroservice.exception.OperationCannotBeCompletedException;
import com.aspiresys.employeemicroservice.repository.EmployeeRepository;
import com.aspiresys.employeemicroservice.service.impl.CounterService;
import com.aspiresys.employeemicroservice.service.impl.EmployeeServiceImpl;
import com.aspiresys.employeemicroservice.util.ApplicationConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class TestEmployeeService.
 */
public class TestEmployeeService {

	/** The employee repository. */
	@Mock
	private EmployeeRepository employeeRepository;

	/** The model mapper. */
	@Mock
	private ModelMapper modelMapper;

	/** The counter service. */
	@Mock
	CounterService counterService;

	/** The employee service. */
	@InjectMocks
	private EmployeeServiceImpl employeeService;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		ModelMapper mapper = new ModelMapper();

		when(modelMapper.map(any(), any())).thenAnswer(i -> {
			return mapper.map(i.getArguments()[0], (Class) i.getArguments()[1]);

		});

		when(employeeRepository.save(any(Employee.class))).thenAnswer(i -> (i.getArguments())[0]);

		when(employeeRepository.findById(anyInt())).thenAnswer(args -> {
			int id=Integer.parseInt(args.getArguments()[0].toString());
			if(id<=0)
				return Optional.empty();
			Employee employee = new Employee();
			 employee.setEmployeeId(id);
//			employee.setEmployeeId(2);
			employee.setEmployeeName("Employee2");
			employee.setEmployeeSL("ServiceLine");
			employee.setEmployeeProject("EmployeeProject");
			
			return Optional.of(employee);
		});

		when(counterService.getNextSequence(ApplicationConstants.EMPLOYEE_COLLECTION_NAME)).thenReturn(2);

	}

	/**
	 * Test add employee.
	 *
	 * @throws OperationCannotBeCompletedException the operation cannot be completed exception
	 */
	@Test
	public void testAddEmployee() throws OperationCannotBeCompletedException {
		EmployeeDTO employeeDTO = getEmployeeDTO(0, "Employee1", "ServiceLine", "EmployeeProject", "O+");
		int employeeInserted = employeeService.addEmployee(employeeDTO);
		Assert.assertTrue(employeeInserted > 0);

	}

	
	/**
	 * Test retrieve single employee.
	 *
	 * @throws EmployeeNotFoundException the employee not found exception
	 */
	@Test(expected = EmployeeNotFoundException.class)
	public void testRetrieveSingleEmployee() throws EmployeeNotFoundException {
		employeeService.retrieveSingleEmployee(0);
		
	}

	/**
	 * Test update employee.
	 *
	 * @throws OperationCannotBeCompletedException the operation cannot be completed exception
	 */
	@Test
	public void testUpdateEmployee() throws OperationCannotBeCompletedException {
		EmployeeDTO employeeDTO = getEmployeeDTO(0, "Employee3", "EmployeeSL", "EmployeeProject", "O+");
		int checkReturn = employeeService.updateEmployee(1, employeeDTO);
		Assert.assertTrue(checkReturn > 0);
	}
	
	/**
	 * Test add employee 2.
	 *
	 * @throws OperationCannotBeCompletedException the operation cannot be completed exception
	 */
	@Test(expected = OperationCannotBeCompletedException.class)
	public void testAddEmployee2() throws OperationCannotBeCompletedException {
		EmployeeDTO employeeDTO = getEmployeeDTO(0, null, "EmployeeSL", "EmployeeProject", "O+");
		employeeService.addEmployee(employeeDTO);
	}

	/**
	 * Gets the employee DTO.
	 *
	 * @param id the id
	 * @param name the name
	 * @param SL the sl
	 * @param project the project
	 * @param bloodGroup the blood group
	 * @return the employee DTO
	 */
	private EmployeeDTO getEmployeeDTO(int id, String name, String SL, String project, String bloodGroup) {
		EmployeeDTO employee = new EmployeeDTO();
		employee.setEmployeeId(id);
		employee.setEmployeeName(name);
		employee.setEmployeeSL(SL);
		employee.setEmployeeProject(project);
		employee.setEmployeeBloodGroup(bloodGroup);
		return employee;
	}
}
