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
	}
	
	public void setPreferences(Vector<Course> prefs) 
	{
		this.preferences = prefs;
	}
	
	public void addAllotedCourse(Course course)
	{
		this.allotedCourses.addElement(course);
		this.credits += course.getCredits();
	}
	public int getTotalCredits(){
        return this.credits;
    }	
	
	public Course[] getAllotedCourses()
	{
		Course[] c = new Course[allotedCourses.size()];
		return allotedCourses.toArray(c);
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
		return this.getName() + " "+ prNumber + "\n" + preferences + "\n" + allotedCourses + "\n" + timetable;
	}

	
}
