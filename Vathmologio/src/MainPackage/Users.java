package MainPackage;

public class Users {
	protected String name,surname,username,department,course;
	protected int usercounter=0;
	
	Users()
	{
		this.setUsercounter(usercounter++);
	}
	
	
	 public void setName(String name)
	 {
		 this.name=name;
	 }
	
	 public void setUsername(String username)
	 {
		 this.username=username;
	 }
	 
	 public void setSurname(String surname)
	 {
		 this.surname=surname;
	 }
	 
	 public void setDepName(String department)
	 {
		 this.department=department;
	 }
	
	 
	 public void setUsercounter(int usercounter)
	 {
		 this.usercounter=usercounter;
	 }
	 
	 
	 
	 
	 public String getName()
	 {
		 return name;
	 } 
	 
	 public String getSurname()
	 {
		 return surname;
	 }
	 public String getUsername()
	 {
		 return username;
	 }

	 public String getDepName()
	 {
		 return department;
	 }
	 
	 
	 public int  getUsercounter()
	 {
		 usercounter++;
		 return usercounter;
	 }
	 
	 public void LogIn()
	 {
		 
	 }
	 
	 public void LogOut()
	 {
		 
	 }
}
