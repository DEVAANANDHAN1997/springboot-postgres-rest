package com.employeeApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeApplication.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}
