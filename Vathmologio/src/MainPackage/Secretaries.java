package MainPackage;

public class Secretaries extends Users{

	Users sd = new Users();
	Professors p =new Professors();
	Courses c = new Courses();
	
	
	
	
	public void SetProfessor(String n,String e,String u,String d,String afm)
	{
		sd.setName(n);
		sd.setSurname(e);
		sd.setUsername(u);
		sd.setDepName(d);
		
		p.setafm(afm);
	
	}
	
	public void SetStudent(String n,String e,String u,String d,int am)
	{
		
		
		
		sd.setName(n);
		sd.setSurname(e);
		sd.setUsername(u);
		
		sd.setDepName(d);
		Students s = new Students(am);
		
		
	}
	
	public void SetCourses(String t,String x,String k,String d)
	{
		c.setTitle(t);
		c.setCourseSem(x);
		c.setProfessor(k);
		c.setCourseDeptname(d);
		
	}
	
	public void CreateGradeList()
	{
		System.out.println("Η λίστα φοιτητών για το Χ μάθημα δημιουργήθηκε.");
	}
	
}
