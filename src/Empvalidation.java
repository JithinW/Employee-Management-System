import java.util.Scanner;

public class Empvalidation {
	
	public String id, name, phone, email;
	static Scanner s = new Scanner(System.in);
	public  String getId(String id){
		
		System.out.println("Enter the Employee ID :");
		id = s.next();
		
		if(id.matches("[E][0-9]+") == false){
			System.out.println("Employee Id must starts with 'E' and the rest with digits \n\neg:E1\n");
			return (getId(id));
		}
		this.id=id;
		return getName(null);
	}
	
	public String getName(String name){
		
		System.out.println("Enter the Employee Name :");
		name = s.next();
		
		if (name.matches("[0-9]+"))
		{
			System.out.println("Name cannot be a numeric, please provide valid name");
			return getName(null);
		}
		if (name.matches("[A-Za-z_]+") == false)
		{
			System.out.println("Please seperate your firstname and second name using underscore'_'.\n\neg: Joby_John");
			return (getName(name));
		}
		this.name = name;
		return getPhone(null);
	}
	
	public String getPhone(String phone){
		
		System.out.println("Enter the Phone Number :");
		phone = s.next();
		if (phone.matches("[0-9]{10}") == false)
		{
			System.out.println("Please enter a valid phone number");
			return (getPhone(phone));
		}
		this.phone=phone;
		return getEmail(null);
	}
	
	public String getEmail(String email){
		
		System.out.println("Enter the Email ID:");
		email = s.next();
		if (email.matches("^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$") == false)
		{
			System.out.println("Please enter a valid email Id");
			return (getEmail(email));
		}
		this.email=email;
		return null;
		
	}
	
	public void display(){
		
		System.out.println("\nEmployee Details\n");
		System.out.println("Employee ID : "+id+"\nEmployee Name : "+name+"\nPhone Number : "+phone+"\nEmail Id : "+email+"");
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Empvalidation obj = new Empvalidation();
		obj.getId(null);
		obj.display();
	}
	}

		