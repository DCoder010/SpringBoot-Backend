package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import net.javaguides.springboot.model.Employee;

// dont need to add @Repository annotation cuz spring data jpa already implements that internally
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
