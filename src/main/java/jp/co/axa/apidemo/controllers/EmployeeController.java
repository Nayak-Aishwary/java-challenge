package jp.co.axa.apidemo.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;

/**
 * The EmployeeController class is a RESTful controller responsible for handling
 * employee-related operations. It provides endpoints for retrieving, creating,
 * updating, and deleting employee information. This controller facilitates the
 * interaction between the API and the underlying service layer to manage
 * employee data.
 */
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * Get a list of all employees.
	 *
	 * @return A list of employees.
	 */
	@GetMapping("/getAll")
	public List<Employee> getAllEmployees() {
		List<Employee> employees = employeeService.retrieveAllEmployees();
		return employees;
	}

	/**
	 * Get an employee by their ID.
	 *
	 * @param employeeId The ID of the employee to retrieve.
	 * @return A ResponseEntity with the retrieved employee and an HTTP status code.
	 */
	@GetMapping("/getById/{employeeId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "employeeId") Long employeeId) {
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
	}

	/**
	 * Get a list of employees by their name.
	 *
	 * @param empName The name to search for.
	 * @return A ResponseEntity with a list of employees matching the name and an
	 *         HTTP status code.
	 */
	@GetMapping("/getByName/{empName}")
	public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable(name = "empName") String empName) {
		List<Employee> employees = employeeService.getEmployeeByName(empName);
		if (employees.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
		}
		return ResponseEntity.ok(employees);
	}

	/**
	 * Register a new employee.
	 *
	 * @param employee The employee to register.
	 * @return A ResponseEntity with the registered employee and an HTTP status
	 *         code.
	 */
	@PostMapping("/register")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}

	/**
	 * Delete an employee by their ID.
	 *
	 * @param employeeId The ID of the employee to delete.
	 * @return A ResponseEntity with a message confirming the deletion and an HTTP
	 *         status code.
	 */
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
		employeeService.deleteEmployee(employeeId);
		return new ResponseEntity<String>("Employee deleted successfully with employee ID: " + employeeId,
				HttpStatus.OK);
	}

	/**
	 * Update an employee by their ID.
	 *
	 * @param employee   The updated employee information.
	 * @param employeeId The ID of the employee to update.
	 * @return A ResponseEntity with the updated employee and an HTTP status code.
	 */
	@PutMapping("/update/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,
			@PathVariable(name = "employeeId") Long employeeId) {
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, employeeId), HttpStatus.OK);
	}

}