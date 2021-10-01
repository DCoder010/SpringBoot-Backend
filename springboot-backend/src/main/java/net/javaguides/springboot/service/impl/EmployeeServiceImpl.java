package net.javaguides.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.service.EmployeeService;

@Service
// no need to add @Transaction annotation cuz spring data jpa internally 
// provides transaction to all its methods
public class EmployeeServiceImpl implements EmployeeService{
	
	private EmployeeRepository employeeRepository;
	
	// no need to add @Autowired annotation cuz whenever spring boot
	// finds spring beans having only 1 constructor it automatically autowires this dependency
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeByID(long id) {
//		Optional<Employee> employee = employeeRepository.findById(id);
//		
//		if(employee.isPresent()) {
//			return employee.get();
//		}
//		else {
//			throw new ResourceNotFoundException("Employee", "id", id);
//		}
		
		// by lambda expression
		
		return employeeRepository.findById(id).orElseThrow(() -> 
			new ResourceNotFoundException("Employee", "id", id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
		// we need to check whether employee with given id exists in a DB or not
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Employee", "id", id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail()); 
		
		// save existing employee to DB
		employeeRepository.save(existingEmployee);
		
		return existingEmployee;
		
	}

	@Override
	public void deleteEmployee(long id) {
		
		// check whether a employee exists in a DB or not
		employeeRepository.findById(id).orElseThrow(() -> 
				new ResourceNotFoundException("Employee", "id", id));
		employeeRepository.deleteById(id);
		
	}

}
