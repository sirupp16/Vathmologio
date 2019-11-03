package MainPackage;

public class Professors extends Users{

	
private String afm;
	
	
	public void setGrade(int grade){
		
	}
    public void setafm(String afm){
		this.afm=afm;
	}
    public String getafm(){
    	return afm;
	}
    
    public void SetStudentsGrades()
    {
    	System.out.println("Οι βαθμοί για τους φοιτητές μπήκανε με επιτυχία.");
    }
    
    public void ShowStudentsGrades()
    {
    	
    	System.out.println("Φοιτητής 1: Y");
    	System.out.println("Φοιτητής 2: Y");
    	System.out.println("Φοιτητής 3: Y");
    	System.out.println("Φοιτητής 4: Y");
    	System.out.println("Φοιτητής 5: Y");
    	System.out.println("Φοιτητής 6: Y");
    }

}
