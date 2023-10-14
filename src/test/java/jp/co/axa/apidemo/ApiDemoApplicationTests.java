package jp.co.axa.apidemo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiDemoApplicationTests {

	@Autowired
	EmployeeRepository empRepo;

	@Test
	public void testSaveEmployee() {

		Employee emp1 = new Employee();
		emp1.setId(1L);
		emp1.setName("Kobayashi Eiji");
		emp1.setSalary(450000);
		emp1.setDepartment("Sales");	
		empRepo.save(emp1);
		
		Employee emp2 = new Employee();
		emp2.setId(2L);
		emp2.setName("Kyotsuke Oka");
		emp2.setSalary(500000);
		emp2.setDepartment("HR");	
		empRepo.save(emp2);
		
		Employee emp3 = new Employee();
		emp3.setId(3L);
		emp3.setName("Nakamura Honma");
		emp3.setSalary(550000);
		emp3.setDepartment("Recruitment");	
		empRepo.save(emp3);
		
		assertNotNull(empRepo.findById(1L).get());
	}
	
	
	@Test
    public void testGetEmployeeById() {
    	Employee emp = empRepo.findById(1L).get();
    	assertEquals("Kobayashi Eiji", emp.getName());
    }

	@Test
	public void testDeleteEmployee() {
    	empRepo.deleteById(3L);
    	assertThat(empRepo.existsById(3L)).isFalse();
    }

	@Test
    public void testUpdateEmployee() {
		Employee emp = empRepo.findById(1L).get();
		emp.setSalary(600000);
		empRepo.save(emp);
		assertNotEquals(450000.0, empRepo.findById(1L).get().getSalary());
    }
	
	@Test
	public void testRetrieveAllEmployees() {
		List<Employee> empList = empRepo.findAll();
		assertThat(empList).size().isEqualTo(2);
	}
}
