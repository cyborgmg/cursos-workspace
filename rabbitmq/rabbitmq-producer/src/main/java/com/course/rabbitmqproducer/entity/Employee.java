package com.course.rabbitmqproducer.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {

	@JsonProperty("employee_id")
	private String employeeId;
	private String name;
	@JsonProperty("bith_date")
	@JsonFormat(pattern="dd/MM/yyyy", locale = "pt-BR", timezone = "America/Sao_Paulo")
	private Date bithDate;

	public Employee(String employeeId, String name, Date bithDate) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.bithDate = bithDate;
	}

	public Date getBithDate() {
		return bithDate;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getName() {
		return name;
	}

	public void setBithDate(Date bithDate) {
		this.bithDate = bithDate;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setName(String name) {
		this.name = name;
	}

}
