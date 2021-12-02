import java.util.Vector;

public class Course{
    private String id, name, timingCode;
    private int credits, seatsTotal, seatsAvailable;
    private Timetable timetable;
    private Vector<Student> studentsAlloted;
    
    Course (String id, String name, String timingCode, int credits, int seatsTotal)
    {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.timingCode = timingCode;
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

    public Vector<Student> getStudentsAlloted()
    {
        return studentsAlloted;
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
    public String getTimingCode()
    {
        return timingCode;
    }
    public String getName()
    {
        return name;
    }

    public String toString()
    {
        return id + " " + name + " Credits: " + credits + " Timing :" + timingCode;
    }

    public String getID() {
	    return this.id;
    }
}