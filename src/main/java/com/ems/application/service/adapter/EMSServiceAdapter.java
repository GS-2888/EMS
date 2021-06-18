package com.ems.application.service.adapter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;



import com.ems.application.service.api.EMSService;
import com.ems.domain.model.Employee;
import com.ems.domain.model.PaySlip;
import com.ems.domain.model.SalaryResponse;
import com.ems.domain.spi.EMSPersistancePort;
import com.google.common.collect.ImmutableMap;
import com.lowagie.text.pdf.PdfWriter;

import io.opentracing.Span;
import io.opentracing.Tracer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class EMSServiceAdapter implements EMSService{
	

	private EMSPersistancePort emsPersistancePort;
	
	private Tracer tracer;
	
	@Autowired
	public EMSServiceAdapter(EMSPersistancePort emsPersistancePort,Tracer tracer) {
		this.emsPersistancePort = emsPersistancePort;
		this.tracer  = tracer;
	}

	@Override
	public void save(Employee employee,Span rootSpan) {
		// TODO Auto-generated method stub
		Span span  = tracer.buildSpan("Service - save").asChildOf(rootSpan).start();
		emsPersistancePort.addEmployee(employee);
		span.log("Service - Event  - employee added");
		span.finish();
	}

	@Override
	public List<Employee> getAllEmployees(Span rootSpan) {
		// TODO Auto-generated method stub
		Span span  = tracer.buildSpan("Service - getAllEmployees").asChildOf(rootSpan).start();
		List<Employee> employees = emsPersistancePort.getAllEmployees();
		span.log("Service - Event  - employees list returned");
		
		span.finish();
		return employees;
	}

	@Override
	public Employee findEmployeeById(int employeeId) {
		// TODO Auto-generated method stub
		
		Employee employee = emsPersistancePort.getEmployee(employeeId); 
		
		return employee;
	}

	@Override
	public void deleteById(int employeeId) {
		// TODO Auto-generated method stub
		emsPersistancePort.deleteEmployee(employeeId);
	}

	@Override
	public List<Employee> getPagedEmployees(String pageNumber, String pageSize) {
		// TODO Auto-generated method stub
		
		List<Employee> employeeList = emsPersistancePort.getPagedEmployees(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
		return employeeList;
	}

	@Override
	public List<Employee> getSortedEmployee(String sortString) {
		// TODO Auto-generated method stub
		return emsPersistancePort.getSortedEmployees(sortString);
		 
	}

	@Override	
	public void updateEmailById(String email, int Id) {
		// TODO Auto-generated method stub
		emsPersistancePort.updateEmployeeSetEmailForId(email, Id);
	}

	@Override
	public ByteArrayOutputStream getPayslip(Employee employee, SalaryResponse salary) throws JRException {
		// TODO Auto-generated method stub
		JasperReport report = (JasperReport)JRLoader.loadObject(getClass().getClassLoader().getResourceAsStream("payslip.jasper"));
		
		List<PaySlip> modelList = new ArrayList<PaySlip>();
				
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		PaySlip payslip = mapper.map(salary,PaySlip.class); 
		
		payslip.setName(employee.getFirstName() + " "+ employee.getLastName());
		payslip.setGrade(employee.getGrade());
		payslip.setPan(employee.getPan());
		Double total = salary.getAllowances()+salary.getBasic()+salary.getHra();
		payslip.setEarningsTotal(total.toString());
		total = salary.getIncometax()+salary.getPf();
		payslip.setDeductionsTotal(total.toString());
		total = Double.parseDouble(payslip.getEarningsTotal()) - Double.parseDouble(payslip.getDeductionsTotal()); 
		payslip.setNetPay(total.toString());
		modelList.add(payslip);
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(modelList);

		Map<String,Object> params = new HashMap<String,Object>();		
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
			
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		configuration.setPermissions(PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING);
		exporter.setConfiguration(configuration);		
		exporter.exportReport();
		
		return outputStream;
	}
	
	}
