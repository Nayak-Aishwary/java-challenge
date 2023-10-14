package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.entities.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query(value = "SELECT e FROM Employee e WHERE e.name = ?1")
	List<Employee> findByName(String name);
}
