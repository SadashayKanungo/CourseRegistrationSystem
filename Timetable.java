import java.util.HashMap;
import java.util.Arrays;
public class Timetable
{
    private String [] table;
    private static final int TT_SIZE = 60;
    private static HashMap <String,Integer> days;
    
    
    private static int[] parseTimingCode(String code){
        String[] parts = code.split(" ");
        int hour = Integer.parseInt(parts[parts.length-1]) - 1;
        int[] indexes = new int[parts.length-1];
        for(int i=0; i<indexes.length; i++){
            indexes[i] = days.get(parts[i]) + hour;
            //System.out.print(indexes[i] + " ");
        }
        //System.out.print("\n");
        return indexes;        
    }

    Timetable ()
    {
        table = new String[TT_SIZE];
        Arrays.fill(table, "");
    }
    
    Timetable (String courseName, String[] timingCodes)
    {
        days = new HashMap<>();
        days.put("M",0);
        days.put("T",10);
        days.put("W",20);
        days.put("Th",30);
        days.put("F",40);
        days.put("S",50);

        table = new String[TT_SIZE];
        Arrays.fill(table, "");

        for(int i=0; i<timingCodes.length; i++){
            int[] indexes = this.parseTimingCode(timingCodes[i]);
            for(int j=0; j<indexes.length; j++){
                this.table[indexes[j]] = courseName;
            }
        }
    }
    
    public String[] getTimetable ()
    {
        return table;
    }

    public boolean match (Timetable tt)
    {
        for (int i = 0; i < TT_SIZE; i++)
        {
            if ((tt.table[i] != "") && (this.table[i] != ""))
            {
                return false;
            }
        }
        return true;
    }

    public void merge (Timetable tt)
    {
        for (int i = 0; i < TT_SIZE; i++)
        {
            if ((tt.table[i] != ""))
            {
                this.table[i] = tt.table[i];
            }
        }
    }

    public String toString ()
    {
        String s = "\t\tMON\t\tTUE\t\tWED\t\tTHU\t\tFRI\t\tSAT\n";
        int x = 0;
        for (int i = 0; i< 10; i++)
        {
            int hour = x+8 > 12 ? (x+8)%12+1 : x+8;
            s = s + hour +"-" + (hour+1) + "\t\t";
            
            for (int j = 0; j < 6; j++)
            {
                s = s + this.table[i + j*10] + "\t";
                if(this.table[i + j*10] == ""){
                    s = s + "\t";
                }
            }
            s = s + "\n";
            x++;
        }
        return s;
    }
}