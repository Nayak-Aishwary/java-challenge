package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> retrieveAllEmployees();

    public Employee getEmployeeById(Long employeeId);
    
    public List<Employee> getEmployeeByName(String empName);

    public Employee saveEmployee(Employee employee);

    public void deleteEmployee(Long employeeId);

    public Employee updateEmployee(Employee employee, Long id);
}