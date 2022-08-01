package com.application.EmployeeManagementSystem;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ListIterator;
import java.util.Scanner;

public class EmployeeManagement extends Employee implements iEmployeeFunction{
	
	boolean found,flag = false;
	Scanner employeeScanner = new Scanner(System.in);
	public static ArrayList<Employee> empList=new ArrayList<Employee>(); 
	
	EmployeeManagement(){
	}

	//Menus for Admin
	public void adminMenu(){
		try{
		int choice;
		Scanner choiceScanner = new Scanner (System.in);
		boolean options = false;
		do{
		System.out.print("\n1. ADD EMPLOYEE\n"
				+"2. VIEW EMPLOYEE\n"
				+"3. SEARCH EMPLOYEE\n"
				+"4. UPDATE EMPLOYEE\n"
				+"5. DELETE EMPLOYEE\n"
				+"6. LOG OUT\n");
		
		System.out.println("\nEnter your Choice : ");
		choice=choiceScanner.nextInt();
		switch (choice)
		{
		case 1:addEmployee();
		break;
		case 2:viewEmployee();
		break;
		case 3:searchEmployee();
		break;
		case 4:updateEmployee();
		break;
		case 5:deleteEmployee();
		break;
		case 6:
		System.out.print("\nSuccessfully Logged out\n\n");
		login();
		break;
		default: 
		System.out.print("Please choose a valid option");
		break;
		}
		}while(!options);
		}
		catch(InputMismatchException inputMissmatchException){
			System.out.println("Error : "+inputMissmatchException);
		}
		finally{
			adminMenu();
		}
	}
	
	//Index page
	public void login(){
		EmployeeServices employeeServices = new EmployeeServices();
		String choice;
		System.out.println("*******************Welcome to Employee Management System*******************");
		System.out.print("\n\n1. Admin Login\n2. Employee Login\n3. Exit\n\nEnter your choice:");
		choice = employeeScanner.next();
		switch(choice)
		{
		case "1":	adminLogin();
		break;
		case "2":	employeeServices.employeeLogin();
		break;
		case "3": System.exit(0);
		default:System.out.print("\nInvalid Choice!");
		break;	
		}
	}
	
	//Admin login page
	public void adminLogin(){
		String username,password;
		boolean authentication = false;
		do
		{
		System.out.print("\nEnter Username:");
		username = employeeScanner.next();
		System.out.print("Enter Password:");
		password = employeeScanner.next();
		if ((username.equals("admin")) && (password.equals("admin"))){
			authentication = true;
			System.out.println("\nSuccessfully Logged in");
			adminMenu();
		}
		else {
			break;
		}
		}while(!authentication);
		System.out.println("\nInvalid username or password, please try again");
		System.out.println("\nPress 1 to continue or 2 to go back main page");
		String choice = employeeScanner.next();
		if(choice.equals("1")){
			adminLogin();
		}
		else if (choice.equals("2")){
			login();
		}
		else{
			System.out.println("\nInvalid choice!");
			adminLogin();
	    }
	}
	
	//Add Employee Details
	public void addEmployee(){
		validateId();
		validateEmpName();
		validateDepartment();
		validateRole();
		validatePhone();
		validateSalary();
		validatePassword();
		Employee employee = new Employee(getEmployeeId(),getEmployeeName(),getDepartment(),getRole(),getPhone(),calculateSalary(getSalary()),getPassword());
		empList.add(employee);
		System.out.print("\nEmployee record added successfully\n");
	}
	
	//View employee details
	public void viewEmployee(){
		found = false;
	    if(empList.size()>0){
	    	System.out.print("\n****************************Employee Details**************************\n\n");
		for (Employee emp: empList){
			System.out.println(emp);
		}
	    }
	    else{
	    	System.out.println("\nNo details are added yet");
	    	adminMenu();
	    }
	}
	
	//Search employee details
	public void searchEmployee(){
		found = false;
		if (empList.size()==0){
			System.out.println("\nNo details are added yet");
			adminMenu();
			}
		System.out.print("\nEnter Id of employee you want to search : ");
		String id=employeeScanner.next();
		for(Employee emp:empList) {
			if(emp.getEmployeeId().equals(id)) {
				found=true;
				System.out.print("\nEmployee Found!\n\n");
				System.out.println(emp);
			}	
		}
		if(!found) {
			System.out.print("\nError : No employees with Id - " +id+"\n");
		}
	}
	
	//Update employee details
	public void updateEmployee(){
		found =false;
		if (empList.size()>0){
			for (Employee emp:empList){
				System.out.print("\n****************************Employee Details**************************\n\n");
				System.out.println("\n" + emp);
			}
		}
		else{
				System.out.println("\nNo details are added yet");
				adminMenu();	
			}
		System.out.print("\nEnter Id of employee You want to update: ");
		String id=employeeScanner.next();
		for(Employee es:empList){
			if(es.getEmployeeId().equals(id)) {
				found=true;
				System.out.print("\nEmployee Found!\n\n");
				System.out.println(es);
				boolean y=false;
				do{
				System.out.print("\n1. Edit Name\n2. Edit Department\n3. Edit Role\n4. Edit Phone\n5. Edit Salary\n6. Edit Password\n\nEnter your choice :");
				String choice = employeeScanner.next();
				switch(choice){
				case "1": updateEmpName(id);
				break;
				case "2": 
					validateDepartment();
					for(Employee e:empList) {
						if(e.getEmployeeId().equals(id)) {
							e.setDepartment(getDepartment());
							System.out.println("\nDepartment name updated successfully!\n");
							System.out.print(e);
						}
					}
				break;
				case "3": 
					validateRole();
					for(Employee e:empList) {
						if(e.getEmployeeId().equals(id)) {
							e.setRole(getRole());
							System.out.println("\nRole updated successfully!\n");
							System.out.print(e);
						}
					}
				break;
				case "4": updateEmpPhone(id);
				break;
				case "5": 
					validateSalary();
					for(Employee e:empList) {
						if(e.getEmployeeId().equals(id)) {
							e.setSalary(calculateSalary(getSalary()));
							System.out.println("\nSalary updated successfully!\n");
							System.out.print(e);
						}
					}
				break;
				case "6": 		
				    validatePassword();
				    for(Employee e:empList) {
					    if(e.getEmployeeId().equals(id)) {
						   e.setPassword(getPassword());
						   System.out.println("\nEmployee password updated successfully!\n");
						   System.out.print(e);
					}
				}
				break;
				default: System.out.println("Invalid Choice, please try again");
				break;
				}
				System.out.print("\n\nEnter 'Y' to edit or 'N' to go back to main page");
				String ans = employeeScanner.next();
				if (ans.equals("Y") || ans.equals("y")){
				y=true;
				}
				else{
					adminMenu();
					break;
				}
				}while(y);
				}	
			}
		 if(!found) {
			System.out.println("\nError : No employees with Id - " +id+"\n");
			updateEmployee();
		}
	}
	
	public void updateEmpName(String id){
		validateEmpName();
		for(Employee e:empList) {
			if(e.getEmployeeId().equals(id)) {
				e.setEmployeeName(getEmployeeName());
				System.out.println("\nEmployee name updated successfully!\n");
				System.out.print(e);
			}
		}
	}
	
	public void updateEmpPhone(String id){
	    validatePhone();
	    for(Employee e:empList) {
		    if(e.getEmployeeId().equals(id)) {
			e.setPhone(getPhone());
			System.out.println("\nPhone no. updated successfully!\n");
			System.out.print(e);
		    }
	    }
	}
	
	public void updateEmpPassword(String id){

	}
	
	//Delete Employees
	public void deleteEmployee(){
		found = false;
		if (empList.size()>0){
		for (Employee emp:empList){
			System.out.println("\n" + emp);
		}
		}
		else{
			System.out.println("\nNo details are added yet");
			adminMenu();
		}
		System.out.println("\nEnter the Id of record you want to delete : ");
		String id=employeeScanner.next();
		for(Employee emp:empList){
			if(emp.getEmployeeId().equals(id)){
				found=true;
				empList.remove(emp);
				System.out.println("\nThe record is deleted\n");
				if (empList.size()>0){
					for (Employee employeeList:empList){
						System.out.println("\n" + employeeList);
					}
				}
			}
			if (!found){
				System.out.println("\nError : No employees with id " + id);
			}
		}
	}
	
        //Calculate Salary	
        public double calculateSalary(double basicSalary){
		double dearnessAllowance,homeRentAllowance,providentFund,grossSalary,incomeTax = 0;
		dearnessAllowance=basicSalary/100 * 15;
		homeRentAllowance=(basicSalary+dearnessAllowance)/100 *12;
		providentFund = (basicSalary+dearnessAllowance)/100 * 12;
		grossSalary = basicSalary + dearnessAllowance + homeRentAllowance;
		double annualIncome = grossSalary*12;
		if ((annualIncome)< 250000){
			incomeTax = 0;
		}
		else if ((annualIncome)>250000 && (annualIncome)<500000){
			incomeTax = (((grossSalary*12) - 250000)/ 100 * 5)/12; 	
		}
		else if ((annualIncome) >500000 && (annualIncome)<1000000){
			incomeTax = (((grossSalary*12) - 500000) / 100 * 20 + 25000)/12;
		}
		else if ((annualIncome)>1000000){
			incomeTax = (((grossSalary*12) - 1000000) / 100 * 30 + 112500)/12;
		}
		double netSalary = grossSalary - (incomeTax + providentFund);	
		return netSalary;
	}
        
        //Employee Id validation
        public void validateId(){
        	do {
        		flag = false;
    	    	System.out.print("\nEnter employee Id:");
    	    	String id=employeeScanner.next();
    	    	if(id.matches("E[0-9]+")==false){
        			System.out.println("\nEmployee Id must starts with 'E' and the rest with digits, eg: E1\n");
    	    	flag = true;
    	    	}
    	    	for (Employee emp:EmployeeManagement.empList){
        	    	if(id.equals(emp.getEmployeeId()))
        	    		System.out.println("\nError: User already exist with Id - "+id+", please use another one");
        	    	flag=true;
    	    	}
    	    	setEmployeeId(id);
    	    	}while(flag);
        }
        
        //Employee name validation
        public void validateEmpName(){
        	do {
    	    	flag = false;
    	    	System.out.print("\nEnter employee name:");
    	    	String name=employeeScanner.next();
    	    	if (name.matches("[A-Za-z_]+") == false){
    	    	System.out.println("Please provide a valid name and seperate your firstname and second name using underscore");
    	    	flag = true;
    	    	}
    	    	setEmployeeName(name);
    	    	}while(flag);
        }
        
        //Employee department validation
        public void validateDepartment(){
        do {
	    	flag = false;
	    	System.out.print("\nEnter Department name:");
	    	String department=employeeScanner.next();
	    	if ((department.matches("[0-9]+")) || (department.matches("[A-Za-z_]+") == false))
    		{
    		System.out.println("Please enter valid department name");
    		flag = true;
    		}
	    	setDepartment(department);
	    	}while(flag);
        }
		  
        ////Employee role validation
		 public void validateRole(){
		do {
	    	flag = false;
	    	System.out.print("\nEnter role:");
	    	String role=employeeScanner.next();
	    	if ((role.matches("[0-9]+")) || (role.matches("[A-Za-z_]+") == false))
    		{
    			System.out.println("please enter a valid designation");
    			flag = true;
    		}
	    	setRole(role);
	    	}while(flag);
		 }
		
		//Employee phone validation
		 public void validatePhone(){
		do {
	    	flag = false;
	    	System.out.print("\nEnter Phone number:");
	    	String phone=employeeScanner.next();
	    	if (phone.matches("[0-9]{10}")==false){
    			System.out.println("please provide valid phone number");
    			flag = true;
    		}
	    	setPhone(phone);
	    	}while(flag);
		 }
		 
		//Employee salary validation
		public void validateSalary(){
		do {
	    	flag = false;
	    	System.out.print("\nEnter Basic Salary:");
	    	String bSalary=employeeScanner.next();
	    	if(bSalary.matches("[0-9]+")==false){
    			System.out.println("Please provide valid Salary");
    			flag = true;
    		}
	    	setSalary(Double.parseDouble(bSalary));
	    	}while(flag);
		}
		
		//Validate Employee password
		public void validatePassword(){
		do {
	    	flag = false;
	    	System.out.print("\nEnter Password:");
	    	String password=employeeScanner.next();
	    	if((password.length()>=8) == false){
				System.out.println("\nPassword length must be atleast 8\n");
				flag = true;
	    	}
	    	setPassword(password);
	    	}while(flag);
		}   	        	
}