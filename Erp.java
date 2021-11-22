import java.util.Scanner;
import java.util.Vector;
import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;

public class Erp{
    private static final int CREDIT_LIMIT = 25;
    private static final String adminDataFile = "AdminData.csv";
    private static final String[] stati = {"Closed : Awaiting Student and Course Data", "Open : Accepting Student Preferences", "Closed : Allotment Process Complete"};
    
    
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
        System.out.println("Press Enter to continue...");
        try {System.in.read();}  
        catch(Exception e) {}  
    }
    private static void print(String s){
        System.out.println(s);
        System.out.println("");
    }
    private static void print(String[] s){
        System.out.println("Available Options :");
        for(int i=0;i<s.length;i++){
            System.out.println((i+1) + ". " + s[i]);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static void handleAdminLogin(Scanner sc) throws FileNotFoundException{
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
    private static int handleStudentID(String id_input){
        print("Checking *"+id_input+"*");
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
    private static void handleStudentLogin(Scanner sc, Student s){
        loggedin = s;
        String[] options = {"View Available Courses", "Submit Preferences"};
        
        boolean loop = true;
        while(loop){
            clearScreen();
            print(options);
            int option = sc.nextInt();
            switch(option){
                case 1 :
                    print("availableCourses");
                    pressEnterToContinue();
                    continue;
                case 2 :
                    print("Enter Space-separated nummbers in order of preference");
                    pressEnterToContinue();
                    continue;
                default :
                    print("Invalid Option!");
                    pressEnterToContinue();
                    continue;
            }
        }

    }



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
                        handleAdminLogin(sc);
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
                    int index = handleStudentID(id_input);
                    if(index < 0){
                        print("ID Not Found");
                        pressEnterToContinue();
                        continue;
                    }
                    else{
                        attempt = registeredStudents.get(index);
                        print("ID Found :" + attempt.getName() + "\nEnter Password");
                    }
                    
                    pwd_input = sc.next();
                    if(attempt.verify(pwd_input)){
                        print("STUDENT LOGIN SUCCESSFUL");
                        handleStudentLogin(sc, attempt);
                    }
                    else{
                        print("Incorrect Login Credentials!");
                        pressEnterToContinue();
                    }
                    continue;
            }
            
        }
    }
}