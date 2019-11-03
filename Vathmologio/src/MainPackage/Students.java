package MainPackage;

public class Students extends Users {

	private int semester,year;
	private static int registrationNumber;
	
	Students()
	{
		
	}
	Students(int am)
	{
		
		this.setRegistrationNumber(am);
		
	}
  
	public void setRegistrationNumber(int registrationNumber)
	{
		this.registrationNumber=registrationNumber;
	}
	
	public int getRegistrationNumber()
	{
		return registrationNumber;
	}
	
	public void ShowMyGrades()
	{
		System.out.println("Μαθημα 1:Χ");
		System.out.println("Μαθημα 2:Χ");
		System.out.println("Μαθημα 3:Χ");
		System.out.println("Μαθημα 4:Χ");
		System.out.println("Μαθημα 5:Χ");
	}
	public void ShowGrades()
	{
		
	}
	
	public void AddCourse()
	{
		
	}
}
