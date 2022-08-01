package com.application.EmployeeManagementSystem;
import java.util.Scanner;

public class EmployeeServices {
	Scanner employeeScanner = new Scanner(System.in);
	EmployeeManagement employeeManagement= new EmployeeManagement();
	
    public EmployeeServices(){	
    }

    //Login page for Employees
    public void employeeLogin(){
        System.out.println("\n***************Employee Login***************\n");	
		System.out.print("\nEnter Employee Id:");
		String userId = employeeScanner.next();
		System.out.print("Enter Password:");
		String password = employeeScanner.next();
		if(EmployeeManagement.empList.size()>0){
		for(Employee emp:EmployeeManagement.empList){
			if ((emp.getEmployeeId().equals(userId)) && (emp.getPassword().equals(password))){
				employeeHome(userId);	
			}
			else{
				break;	
				}
		}
		}
			System.out.println("\nError: user not found! Please check your username and password");
			System.out.println("\nPress 1 to continue or 2 to go back main page\n");
			String choice = employeeScanner.next();
			if(choice.equals("1")){
				employeeLogin();
			}
			else if (choice.equals("2")){
				employeeManagement.login();
			}
			else{
				System.out.println("\nInvalid choice!");
				employeeLogin();
		}
	}

    //View employee detail
    public void empViewDetail(String id){
	System.out.println("***************Employee Details***************\n\n");
	for(Employee emp:EmployeeManagement.empList){
		if (emp.getEmployeeId().contains(id)){
		System.out.print(emp);
		}
	}
	employeeHome(id);
    }

    //Update employee detail
    public void empUpdateDetail(String id){
	    if (EmployeeManagement.empList.size()>0){
		for (Employee data:EmployeeManagement.empList){
		if(data.getEmployeeId()==id)
			System.out.println("\n" + data);
		}
	    }
	    else{
			System.out.println("\nNo details to show");
			employeeHome(id);	
		}
			boolean y=false;
	    do{
			for(Employee emp:EmployeeManagement.empList){
				if(emp.getEmployeeId().equals(id)){
					    System.out.println("\n" + emp);
					}
				}
			System.out.print("\n1. Edit Name\n2. Edit Phone Number\n\nEnter your choice :");
			String choice = employeeScanner.next();
			switch(choice){
			case "1": employeeManagement.updateEmpName(id);
			break;
			case "2": employeeManagement.updateEmpPhone(id);
			break;	
			default : System.out.println("\nInvalid Choice, please try again");
			break;
			}
			System.out.print("\n\nEnter 'Y' to edit or 'N' to go back to main page");
			String ans = employeeScanner.next();
			if (ans.equals("Y") || ans.equals("y")){
			y=true;
			}
			else{
				employeeHome(id);
				break;
			}
			}while(y);			
    }

    //Employee home page
    public void employeeHome(String id){
	    System.out.println("\n\n***************Employee home***************");
	    for(Employee emp:EmployeeManagement.empList){
		if(id.equals(emp.getEmployeeId())){
			System.out.println("\nWelcome "+emp.getEmployeeName()+",");
		}
	    }
	    System.out.print("\n1.View Details\n2.Update Details\n3.Change Password\n4.Log out\n\nPlease enter your choice:");
	    String choice = employeeScanner.next();
	    System.out.print("\n");
	    if (choice.equals("1")){
		empViewDetail(id);
	    }
	    else if (choice.equals("2")){
			empUpdateDetail(id);
		}
	    else if(choice.equals("3")){
		employeeManagement.validatePassword();
		for(Employee e:EmployeeManagement.empList) {
			if(e.getEmployeeId().equals(id)) {
					e.setPassword(employeeManagement.getPassword());
					System.out.println("\nYour Password updated successfully!\n");
					System.out.print(e);
			}
		}
			employeeHome(id);
		}
	    else if(choice.equals("4")){
		EmployeeManagement employeeManagement = new EmployeeManagement();
		employeeManagement.login();
	    }
	    else{
		System.out.print("\nEnter a valid choice");
	    }
    }
}