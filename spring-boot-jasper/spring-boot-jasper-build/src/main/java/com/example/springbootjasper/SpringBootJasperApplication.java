package com.example.springbootjasper;

import com.example.springbootjasper.model.Employee;
import com.example.springbootjasper.service.EmployeeService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SpringBootJasperApplication implements CommandLineRunner {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	ResourceLoader resourceLoader;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJasperApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//employeeService.findAll().forEach(e-> System.out.println(e.getName()));
		createPdfReport(employeeService.findAll());
	}

	private void createPdfReport(final List<Employee> employees) throws JRException, IOException {

		Resource resource = resourceLoader.getResource("classpath:reports/reportCustom.jrxml");

		InputStream stream = resource.getInputStream();

		// Compile the Jasper report from .jrxml to .japser
		final JasperReport report = JasperCompileManager.compileReport(stream);

		// Fetching the employees from the data source.
		final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(employees);

		// Adding the additional parameters to the pdf.
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "cyborg");

		// Filling the report with the employee data and additional parameters information.
		final JasperPrint print = JasperFillManager.fillReport(report, parameters, source);

		// Export the report to a PDF file.
		JasperExportManager.exportReportToPdfFile(print, "Employee_report.pdf");
	}
}
