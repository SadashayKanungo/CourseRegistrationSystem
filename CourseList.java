import java.util.Vector;
import java.util.Iterator;

public class CourseList{
	public static Vector<Course> reorder(Vector<Course> list, int[] order) {
		Vector<Course> new_list = new Vector<Course>();

		for(int i=0; i<order.length; i++){
			new_list.addElement(list.get(order[i]-1));
		}
		return new_list;
	}
}