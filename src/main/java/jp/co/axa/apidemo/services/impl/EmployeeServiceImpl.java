package jp.co.axa.apidemo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.ResourceNotFoundException;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import jp.co.axa.apidemo.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> retrieveAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees;
	}

	@Override
	public Employee getEmployeeById(Long employeeId) {
		return employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", employeeId));
	}

	public List<Employee> getEmployeeByName(String empName) {
		List<Employee> employees = employeeRepository.findByName(empName);
		return Optional.ofNullable(employees).filter(list -> !list.isEmpty())
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Name", empName));
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		employeeRepository.save(employee);
		return employee;
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", employeeId));
		employeeRepository.deleteById(employeeId);
	}

	@Override
	public Employee updateEmployee(Employee employee, Long employeeId) {
		Employee existingEmp = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", employeeId));
		existingEmp.setName(employee.getName());
		existingEmp.setSalary(employee.getSalary());
		existingEmp.setDepartment(employee.getDepartment());
		employeeRepository.save(existingEmp);
		return existingEmp;
	}
}