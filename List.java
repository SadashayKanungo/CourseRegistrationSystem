public class List {
    protected final int LIST_SIZE = 250;
    protected int size;
    protected ListItem[] list;

    List(){
        this.list = new ListItem[LIST_SIZE];
        this.size = 0;
    }

    public int getSize(){
        return this.size;
    }
    
    public int getIndexByID(String id){
        for(int i=0;i<size;i++){
            ListItem li = list[i];
            if(li.getID().equals(id)){
                return i;
            }
        }
        return -1;
    }

    public void add(ListItem element) {
        list[size] = element;
        size++;
    }

    public Object[] getAll() {
       return list;
    }
    
     
    public Object get(String id) {
        int index = this.getIndexByID(id);
        return list[index];
    }

    public Object get(int index) {
       return list[index];
    }

    public void remove(int index) {
        for (int i = index; i < size; i++) {
            list[i] = list[i+1];
        }
        size--;
    }

    public void remove(String id) {
        int index = this.getIndexByID(id);
        this.remove(index);
    }

    public String toString(){
		String s = "";
		for(int i=0; i<size; i++){
			s = s + (i+1) + ". " + list[i] + "\n";
		}
		return s;
	}

}
/*
 * System.out.print(" [ "); for (int i = 0; i < obj.length; i++) {
 * System.out.print(" " + obj[i] + " "); } System.out.println("]");
 */

/*
 * for(int i=0;i<obj.length;i++){ if(id == obj[i]){ System.out.println(obj[i]);
 * break; } }
 */

/*
 * List newobj[] = new List[size+1]; for(int i=0;i<size;i++){ newobj[i] =
 * obj[i]; }
 */