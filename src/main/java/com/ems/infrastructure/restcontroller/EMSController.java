package com.ems.infrastructure.restcontroller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ems.domain.model.Employee;
import com.ems.domain.model.EmployeeRequest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import net.sf.jasperreports.engine.JRException;

public interface EMSController {

	@ApiResponse(code = 200, message = "List of employees",response = Employee.class, responseContainer = "List")
	@ApiOperation(value="fetch all employees", notes=" provides list of employees in xml or json.")		
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees(); 
	
	
	@ApiOperation(value="add new employee", notes="provides api to add new employee and returns added employee.",response = Employee.class )
	@PostMapping(path = "/addemployee", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee);
	
	
	
	@ApiOperation(value="delete employee", notes="provides api to delete employee by ID and returns deleted ID.", response=String.class)
	@DeleteMapping("/deleteemployee/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@ApiParam(name="employeeID" ,value="employee ID",required=true)  @PathVariable(value = "employeeId", required = true) Integer employeeId) ;
	
	
	
	@ApiOperation(value="update employee", notes="provides api to update employee details and returns updated employee.", response=Employee.class)
	@PutMapping("/updateemployee")
	public ResponseEntity<Employee> updateEmployee(@ApiParam(name="employee" ,value="employee details",required=true)  @RequestBody EmployeeRequest employee);
	
	
	@ApiResponse(code = 200, message = "List of employees",response = Employee.class, responseContainer = "List")
	@ApiOperation(value="fetch paged employees", notes="provides api to get paged employee list by page number and list size and returns paged employee.")
	@GetMapping("/pagedemployees")
	public ResponseEntity<List<Employee>> getPagedEmployee(@ApiParam(value="pageNumber is parameter to specify the current page.")  @RequestParam(value="pageNumber",required=true) String pageNumber,
			@ApiParam(value="pageSize is parameter to specify the current page.")  @RequestParam(value="pageSize",required=true) String pageSize) ;
	
	
	@ApiResponse(code = 200, message = "List of employees",response = Employee.class, responseContainer = "List")
	@ApiOperation(value="fetch sorted employees", notes="provides api to get sorted employee list by sort string and returns sorted employee.")
	@GetMapping("/sortedemployees")
	public ResponseEntity<List<Employee>> getSotedEmployee(@ApiParam(value="sortString is parameter to sort employees based upon value passed",required = true)
			@RequestParam(value = "sortString", required = true) String sortString);
	
	
	@ApiOperation(value="update employee", notes="provides api to update employee details and returns updated employee.", response=Employee.class)
	@PatchMapping("/updateemployeeemail")
	public ResponseEntity<Employee> updateEmployeeEmail(@ApiParam(name="email" ,value="email details",required=true) @RequestBody com.ems.domain.model.Email email);
	
	
	@GetMapping("/payslip/{employeeId}")
	public ResponseEntity<byte[]> getPaySlip(@PathVariable(value="employeeId",required=true) int employeeId) throws JRException;
}
