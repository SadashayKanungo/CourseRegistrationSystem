import java.util.Vector;

public class Course{
    private String id, name;
    private int credits, seatsTotal, seatsAvailable;
    private Timetable timetable;
    private Vector<Student> studentsAlloted;
    
    Course (String id, String name, String timingCode, int credits, int seatsTotal)
    {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.timetable = new Timetable(id, timingCode.split("/"));
        this.seatsTotal = seatsTotal;
        this.seatsAvailable = seatsTotal;
        this.studentsAlloted = new Vector<Student>();
    }

    public void addStudent (Student student)
    {
        studentsAlloted.addElement(student);
        seatsAvailable = seatsAvailable - 1;
    }

    public Student[] getStudentsAlloted()
    {
        Student[] s = new Student[studentsAlloted.size()];
        return studentsAlloted.toArray(s);
    }

    public boolean hasSeats()
    {
        if (seatsAvailable > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Timetable getTimetable()
    {
        return timetable;
    }

    public int getCredits()
    {
        return credits;
    }

    public String toString()
    {
        return id + " " + name + " " + credits + "\n" + timetable + "\n" + studentsAlloted;
    }

    public String getID() {
	    return this.id;
    }
}