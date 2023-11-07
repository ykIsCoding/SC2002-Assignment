package Utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatabaseUtils {

    public static ArrayList<String[]> getCredentials(String fn){
        //String rex = "\\s*([A-Za-z]+)\\s*([A-Za-z0-9_@.]+)[;]*\\s*([A-Za-z]+)";
        String rex = "\\s*([A-Za-z]+)[|]+([A-Za-z0-9_@.]+)[;]*[|]+([A-Za-z]+)";
        return readFromFile(fn, rex,3);
    }

    public static void setCredentials(String fn,ArrayList<String[]> al){
        writeToFile(fn,al);
    }

    private static boolean writeToFile(String fn, ArrayList<String[]> al){
         try {
            FileWriter writer = new FileWriter(fn, false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.newLine();

            for(int h=0;h<al.size();h++){
                for(int g=0;g<al.get(h).length;g++){
                    bufferedWriter.write(al.get(h)[g]+"|");
                }
                bufferedWriter.newLine();
            } 
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private static ArrayList<String[]> readFromFile(String fn, String rex, int groups){
        ArrayList<String[]> details = new ArrayList<String[]>();
         try {
            FileReader reader = new FileReader(fn);
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String line;
            int count = 0;

            Pattern pat = Pattern.compile(rex, Pattern.CASE_INSENSITIVE);
            
            while ((line = bufferedReader.readLine()) != null) {
                if(count==0) {
                    count++;
                    continue;
                }
                Matcher mat = pat.matcher(line);
                if(mat.find()){
                    String[] temp= new String[groups];
                    for(int v =0;v<groups;v++){
                        temp[v]= mat.group(v+1);
                    }
                    //String temp[] = {mat.group(1).toString(),mat.group(2).toString(),mat.group(3).toString()};
                    details.add(temp);
                }
            }
            reader.close();
            return details;
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return details;
    }
}
