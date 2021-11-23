import java.util.Vector;

public class Student extends User{
	private Vector<Course> preferences , allotedCourses;
	private Timetable timetable;
	private int prNumber, credits;
	
	Student(String id, String password, String name)
	{
		super(id,password,name);
		this.credits = 0;
		this.preferences = new Vector<Course>();
		this.allotedCourses = new Vector<Course>();
		this.timetable = new Timetable();
	}
	
	public void setPreferences(Vector<Course> prefs) 
	{
		System.out.println("Saving Preferences");
		this.preferences = prefs;
	}
	public Vector<Course> getPreferences() 
	{
		return preferences;
	}
	
	public void addAllotedCourse(Course course)
	{
		System.out.println("Alloting Course");
		this.allotedCourses.addElement(course);
		this.credits += course.getCredits();
		this.timetable.merge(course.getTimetable());
	}
	public int getCredits(){
        return this.credits;
    }	
	
	public Vector<Course> getAllotedCourses()
	{
		return allotedCourses;
	}
	
	public Timetable getTimetable() 
	{
		return timetable; 
	}
	
	public void setPRNumber(int pr)
	{
		this.prNumber = pr ;
	}
	
	public int getPRNumber() 
	{
		return prNumber ; 
	}
	
	public String toString()
	{
		return this.getID() + " " + this.getName();
	}

	
}
