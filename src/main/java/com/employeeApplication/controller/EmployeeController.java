package com.employeeApplication.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeApplication.Exception.EmployeeException;
import com.employeeApplication.Repository.EmployeeRepository;
import com.employeeApplication.entity.Employee;

@RestController
@RequestMapping("/backend")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable(value = "id") long employeeId) throws EmployeeException {
		return employeeRepository.findById(employeeId).orElseThrow(()->new EmployeeException("Employee Id doesnt Exist"+employeeId));
	}
	
	@PostMapping("/employee")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@DeleteMapping("/employee/{id}")
	public  ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable long id) throws EmployeeException {
		Employee employee = employeeRepository.findById(id).orElseThrow(()-> new EmployeeException("Employee Id doesnt Exist"+id));
		employeeRepository.delete(employee);
		Map<String,Boolean> response = new HashMap<>();
		response.put("deleted",true);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee empdetails,@PathVariable("id")long id) throws EmployeeException{
		Employee employee = employeeRepository.findById(id).orElseThrow(()-> new EmployeeException("Employee Id doesnt Exist"+id));
		employee.setName(empdetails.getName());
		employee.setDepartment(empdetails.getDepartment());		
		employeeRepository.save(employee);
		return ResponseEntity.ok(employee);
	}
	
	

}
