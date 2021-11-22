import java.util.Scanner;
import java.util.Vector;
import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;

public class Erp{
    private static final int CREDIT_LIMIT = 25;
    private static final String adminDataFile = "AdminData.csv";
    private static final String[] stati = {"Awaiting Student and Course Data", "Accepting Student Preferences", "Allotment Process Complete"};
    
    
    private static Admin admin = null;
    private static Vector<Course> availableCourses = new Vector<Course>();
    private static Vector<Student> registeredStudents = new Vector<Student>();
    private static int statusIndex = 0;

    private static boolean loginLoop = true;
    private static User loggedin = null;

    private static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        if(loggedin == null){
            System.out.println("\tNot Logged In"+"\t\t\t\t Status : "+stati[statusIndex]+"\n");
        } else {
            System.out.println("\tLogged In : "+loggedin.getName()+"\t\t\t Status : "+stati[statusIndex]+"\n");
        }            
    }
    private static void pressEnterToContinue(){ 
        System.out.println("\nPress Enter to continue...");
        try {System.in.read();}  
        catch(Exception e) {}  
    }
    private static void print(String s){
        System.out.println(s);
    }
    private static void print(String[] s){
        System.out.println("Available Options :");
        for(int i=0;i<s.length;i++){
            System.out.println((i+1) + ". " + s[i]);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static void allotCourses(){
        for(int i=0;i<registeredStudents.size();i++){
            Student s = registeredStudents.get(i);
            print("Allotting to "+s.getName());
            for(int j=0;j<s.getPreferences().size();j++){
                Course c = s.getPreferences().get(j);
                if(!c.hasSeats() || s.getCredits()+c.getCredits() > CREDIT_LIMIT){
                    continue;
                }
                if(s.getTimetable().match(c.getTimetable())){
                    s.addAllotedCourse(c);
                    c.addStudent(s);
                }
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////


    private static void handleAdminLogin1(Scanner sc) throws FileNotFoundException{
        loggedin = admin;
        String[] options = {"Submit Course Data", "Submit Student Data", "Begin Registration", "Log Out"};
        
        boolean loop = true;
        while(loop){
            clearScreen();
            print(options);
            int option = sc.nextInt();
            switch(option){
                case 1 :
                    availableCourses = admin.enterCourses();
                    print("Course Data stored");
                    pressEnterToContinue();
                    continue;
                case 2 :
                    registeredStudents = admin.enterStudents();
                    print("Student Data stored");
                    pressEnterToContinue();
                    continue;
                case 3 :
                    statusIndex = 1;
                    registeredStudents = StudentList.prShuffle(registeredStudents);
                    print("Registration Status Updated");
                    pressEnterToContinue();
                    continue;
                case 4 :
                    loggedin = null;
                    loop = false;
                    break;
                default :
                    print("Invalid Option!");
                    pressEnterToContinue();
                    continue;
            }
        }
    }
    private static void handleAdminLogin2(Scanner sc){
        loggedin = admin;
        String[] options = {"End Registration", "Allot Courses", "View Alloted Student List", "Log Out"};
        
        boolean loop = true;
        while(loop){
            clearScreen();
            print(options);
            int option = sc.nextInt();
            switch(option){
                case 1 :
                    statusIndex = 2;
                    print("Registration Status Updated");
                    pressEnterToContinue();
                    continue;
                case 2 :
                    allotCourses();
                    print("Allotment Process Complete");
                    pressEnterToContinue();
                    continue;
                case 3 :
                    print("Enter Course ID");
                    String crs_input = sc.next();
                    int index = handleCourseID(crs_input);
                    Course c = availableCourses.get(index);
                    print("Course : "+ c.getName());
                    print("Alloted Student List");
                    StudentList.print(c.getStudentsAlloted());
                    pressEnterToContinue();
                    continue;
                case 4 :
                    loggedin = null;
                    loop = false;
                    break;
                default :
                    print("Invalid Option!");
                    pressEnterToContinue();
                    continue;
            }
        }
    }
    private static int handleStudentID(String id_input){
        int index = -1;
        int size = registeredStudents.size();
        for(int i=0; i<size; i++){
            Student s = registeredStudents.get(i);
            if(s.getID().equals(id_input)){
                index=i;
                break;
            }
        }
        return index;
    }
    private static int handleCourseID(String id_input){
        int index = -1;
        int size = availableCourses.size();
        for(int i=0; i<size; i++){
            Course c = availableCourses.get(i);
            if(c.getID().equals(id_input)){
                index=i;
                break;
            }
        }
        return index;
    }
    private static void handleStudentLogin1(Scanner sc, Student s){
        loggedin = s;
        String[] options = {"View PR Number", "View Available Courses", "Submit Preferences", "Log Out"};
        
        boolean loop = true;
        while(loop){
            clearScreen();
            print(options);
            int option = sc.nextInt();
            switch(option){
                case 1 :
                    print("Student : "+ s.getName());
                    print("PR Number : "+ s.getPRNumber());
                    pressEnterToContinue();
                    continue;
                case 2 :
                    print("Available Courses");
                    print(CourseList.print(availableCourses));
                    pressEnterToContinue();
                    continue;
                case 3 :
                    print("Enter Comma-separated numbers in order of preference");
                    String pref_input = sc.next();
                    String[] pref_str = pref_input.split(",");
                    int[] pref_order = new int[pref_str.length];
                    for(int i=0;i<pref_order.length;i++){
                        pref_order[i] = Integer.parseInt(pref_str[i]);
                    }
                    s.setPreferences(CourseList.reorder(availableCourses,pref_order));
                    print("Preferences Saved");
                    print(CourseList.print(s.getPreferences()));
                    pressEnterToContinue();
                    continue;
                case 4 :
                    loggedin = null;
                    loop = false;
                    break;
                default :
                    print("Invalid Option!");
                    pressEnterToContinue();
                    continue;
            }
        }

    }
    private static void handleStudentLogin2(Scanner sc, Student s){
        loggedin = s;
        String[] options = {"View Alloted Courses", "View Timetable", "Log Out"};
        
        boolean loop = true;
        while(loop){
            clearScreen();
            print(options);
            int option = sc.nextInt();
            switch(option){
                case 1 :
                    print("Available Courses");
                    print(CourseList.print(availableCourses));
                    pressEnterToContinue();
                    continue;
                case 2 :
                    print("Enter Comma-separated numbers in order of preference");
                    String pref_input = sc.next();
                    String[] pref_str = pref_input.split(",");
                    int[] pref_order = new int[pref_str.length];
                    for(int i=0;i<pref_order.length;i++){
                        pref_order[i] = Integer.parseInt(pref_str[i]);
                    }
                    s.setPreferences(CourseList.reorder(availableCourses,pref_order));
                    print("Preferences Saved");
                    print(CourseList.print(s.getPreferences()));
                    pressEnterToContinue();
                    continue;
                case 3 :
                    loggedin = null;
                    loop = false;
                    break;
                default :
                    print("Invalid Option!");
                    pressEnterToContinue();
                    continue;
            }
        }

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File(adminDataFile));
        String[] adminCredentials = sc.nextLine().split(",");
        admin = new Admin(adminCredentials[0],adminCredentials[1],adminCredentials[2]);

        sc = new Scanner(System.in);
        
        while(loginLoop){
            clearScreen();
            switch(statusIndex){
                case 0:
                    print("ADMIN LOGIN\nEnter Password");
                    String pwd_input = sc.next();
                    if(admin.verify(pwd_input)){
                        print("ADMIN LOGIN SUCCESSFUL");
                        handleAdminLogin1(sc);
                    }
                    else{
                        print("Incorrect Login Credentials!");
                        pressEnterToContinue();
                    }
                    continue;
                case 1:
                    print("STUDENT LOGIN\nEnter ID");
                    Student attempt;
                    String id_input = sc.next();
                    if(admin.getID().equals(id_input)){
                        print("Found ADMIN ID\nEnter Password");
                        pwd_input = sc.next();
                        if(admin.verify(pwd_input)){
                            print("ADMIN LOGIN SUCCESSFUL");
                            handleAdminLogin2(sc);
                        }
                        else{
                            print("Incorrect Login Credentials!");
                            pressEnterToContinue();
                        }
                    }
                    int index = handleStudentID(id_input);
                    if(index < 0){
                        print("ID Not Found");
                        pressEnterToContinue();
                    }
                    else{
                        attempt = registeredStudents.get(index);
                        print("ID Found :" + attempt.getName() + "\nEnter Password");
                        pwd_input = sc.next();
                        if(attempt.verify(pwd_input)){
                            print("STUDENT LOGIN SUCCESSFUL");
                            handleStudentLogin1(sc, attempt);
                        }
                        else{
                            print("Incorrect Login Credentials!");
                            pressEnterToContinue();
                        }
                    }
                    continue;
                case 2:
                    print("STUDENT LOGIN\nEnter ID");
                    id_input = sc.next();
                    if(admin.getID().equals(id_input)){
                        print("Found ADMIN ID\nEnter Password");
                        pwd_input = sc.next();
                        if(admin.verify(pwd_input)){
                            print("ADMIN LOGIN SUCCESSFUL");
                            handleAdminLogin2(sc);
                        }
                        else{
                            print("Incorrect Login Credentials!");
                            pressEnterToContinue();
                        }
                    }
                    index = handleStudentID(id_input);
                    if(index < 0){
                        print("ID Not Found");
                        pressEnterToContinue();
                    }
                    else{
                        attempt = registeredStudents.get(index);
                        print("ID Found :" + attempt.getName() + "\nEnter Password");
                        pwd_input = sc.next();
                        if(attempt.verify(pwd_input)){
                            print("STUDENT LOGIN SUCCESSFUL");
                            handleStudentLogin2(sc, attempt);
                        }
                        else{
                            print("Incorrect Login Credentials!");
                            pressEnterToContinue();
                        }
                    }
                    continue;
                default:
                    break;
            }
            
        }
    }
}