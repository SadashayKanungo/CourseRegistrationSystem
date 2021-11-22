import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Vector;

public class Admin extends User {
    private String studentDataFile = "StudentData.csv";
    private String courseDataFile = "CourseData.csv";

    Admin(String id, String password, String name)
	{
		super(id,password,name);
	}

    // public boolean startRegistration() {
    //     // ERP function allotCourses() uses this
    //     return true;
    // }
    // public boolean endRegistration() {
    //     // ERP function allotCourses() uses this
    //     return false;
    // }

    public Vector<Student> enterStudents() throws FileNotFoundException{
        Scanner sc = new Scanner(new File(studentDataFile));
        Vector<Student> students = new Vector<Student>();
        while(sc.hasNextLine()){
            String[] input = sc.nextLine().split(",");
            Student new_student = new Student(input[0],input[1],input[2]);
            students.addElement(new_student);
        }
        return students;

        // System.out.println("Enter Course Name: ");
        // Scanner sc= new Scanner(System.in);
        // String a = sc.nextLine();
        // ERP function setStudents() invoked
    }
    public Vector<Course> enterCourses() throws FileNotFoundException{
        Scanner sc = new Scanner(new File(courseDataFile));
        Vector<Course> courses = new Vector<Course>();
        while(sc.hasNextLine()){
            String[] input = sc.nextLine().split(",");
            Course new_course = new Course(input[0],input[1],input[2],Integer.parseInt(input[3]),Integer.parseInt(input[4]));
            courses.addElement(new_course);
        }
        return courses;

        // System.out.println("Enter Course Name: ");
        // Scanner sc= new Scanner(System.in);
        // String a = sc.nextLine();
        // ERP function setCourses() invoked
    }
}
