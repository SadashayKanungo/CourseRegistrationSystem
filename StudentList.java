import java.util.Random;
import java.util.Vector;

public class StudentList{
	public static Vector<Student> prShuffle(Vector<Student> list) {
		int size = list.size();
		Random random = new Random();
		int[] randomIndexes = random.ints(0,size).distinct().limit(size).toArray();

		Vector<Student> new_list = new Vector<Student>();

		for(int i=0; i<size; i++){
			Student s = list.get(randomIndexes[i]);
			s.setPRNumber(i+1);
			new_list.addElement(s);
		}

		return new_list;	
	}
	public static String print(Vector<Student> list) {
		int size = list.size();
		String str = "";

		for(int i=0; i<size; i++){
			Student s = list.get(i);
			str += (i+1) + ". " + s.toString() + "\n";
		}
		return str;
	}

}