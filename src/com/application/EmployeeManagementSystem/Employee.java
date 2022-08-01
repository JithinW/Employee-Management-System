package com.application.EmployeeManagementSystem;

public class Employee{
	
	private String employeeId;
	private String employeeName;
	private String department;
	private String role;
	private String phone;
	private double basicSalary;
	private String password;
		
	public Employee(){
	}
	
	public Employee(String employeeId, String employeeName, String department, String role, String phone, double basicSalary,String password) {
		
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.department = department;
		this.role = role;
		this.phone = phone;
		this.basicSalary = basicSalary;
		this.password=password;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String id) {
		this.employeeId = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public double getSalary() {
		return basicSalary;
	}

	public void setSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "| Id - "+employeeId+" | Employee Name - "+employeeName+" | Department - "+department+" | Role - "+role+" | Phone No. - "+phone+" | Net Salary - "+basicSalary+" | Password - "+password;
	}
}