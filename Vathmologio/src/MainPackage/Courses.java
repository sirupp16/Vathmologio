package MainPackage;

public class Courses extends Professors {

	
	private String courseSem;
	private String professor, courseDeptname, title ; 
	
	public void setCourseSem(String courseSem) {
		this.courseSem=courseSem;
	}
    public void setProfessor(String professor) {
    	this.professor=professor;
	}
    public void setCourseDeptname(String courseDeptname) {
    	
    	this.courseDeptname=courseDeptname;
	}
    public void setTitle(String title) {
    	this.title=title;	
	}
	public String getCourseSem () {
		return courseSem;
	}
	public String getProfessor() {
		return professor;
	}
	public String getCourseDeptname() {
		
		return courseDeptname;
	}
	public String getTitle() {
		return title;
	}
	
	
}
