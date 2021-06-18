package com.ems.infrastructure.restcontroller;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient.EurekaServiceInstance;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ems.application.service.api.EMSService;
import com.ems.domain.model.Email;
import com.ems.domain.model.Employee;
import com.ems.domain.model.EmployeeRequest;
import com.ems.domain.model.SalaryResponse;
import com.netflix.appinfo.InstanceInfo;

import io.opentracing.Span;
import io.opentracing.Tracer;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/api")
public class EMSControllerImpl implements EMSController{
	
	private EMSService emsService;
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	Tracer tracer;
	
	@Autowired
	public EMSControllerImpl(EMSService emsService) {
		this.emsService = emsService;
	}

	@Override
	public ResponseEntity<List<Employee>> getAllEmployees() {
		
		Span span  = tracer.buildSpan("Controller - getAllEmployees()").start();
		List<Employee> employees = emsService.getAllEmployees(span);
		span.setTag("http.status_code", 201);
		
		span.finish();
		 return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Employee> addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Span span  = tracer.buildSpan("Controller - addEmployee()").start();
		emsService.save(employee,span);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> deleteEmployee(Integer employeeId) {
		// TODO Auto-generated method stub
		Employee employee = emsService.findEmployeeById(employeeId);
		emsService.deleteById(employeeId);
		return new ResponseEntity<String>("Deleted by employee ID " + employeeId,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Employee> updateEmployee(EmployeeRequest employee) {
		// TODO Auto-generated method stub		
		Span span  = tracer.buildSpan("Controller - updateEmployee()").start();
		Employee employeeModel = mapper.map(employee,Employee.class); 		
		emsService.save(employeeModel,span);
		span.setTag("http.status_code", 200);
		
		span.finish();
		return new ResponseEntity<Employee>(employeeModel, HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<List<Employee>> getPagedEmployee(String pageNumber, String pageSize) {
		// TODO Auto-generated method stub
		List<Employee> employees = null;
		employees = emsService.getPagedEmployees(pageNumber, pageSize);
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Employee>> getSotedEmployee(String sortString) {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<Employee>>(emsService.getSortedEmployee(sortString), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Employee> updateEmployeeEmail(Email email) {
		// TODO Auto-generated method stub
		emsService.updateEmailById(email.getEmail(), email.getId());		
		Employee employee = emsService.findEmployeeById(email.getId());
		return new ResponseEntity<Employee>(employee, HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<byte[]> getPaySlip(int employeeId) throws JRException {
		// TODO Auto-generated method stub
		
		String url = getBaseServiceUrl("emspayroll")+"/api/salary/"+employeeId;
		SalaryResponse salary= restTemplate.getForObject(url,SalaryResponse.class);
		
		Employee employee = emsService.findEmployeeById(employeeId);
		
		ByteArrayOutputStream outputStream = emsService.getPayslip(employee,salary);		
	
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_PDF);	    
	    String filename = "output.pdf";
	    headers.setContentDispositionFormData(filename, filename);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    ResponseEntity<byte[]> byteresponse = new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
	    return byteresponse;	
	}
	
	private String getBaseServiceUrl(String serviceName) {
		
		List<ServiceInstance> instances  = discoveryClient.getInstances(serviceName);
		EurekaServiceInstance instance =  (EurekaServiceInstance)instances.get(0);
		InstanceInfo instanceInfo = (InstanceInfo) instance.getInstanceInfo();
		return "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort();
	}

}
