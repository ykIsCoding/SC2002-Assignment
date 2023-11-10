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
    private static String headerRegExp = ".*\\|USERID\\|.*";
    private static String userListHeader = "userid|name|email|username|password|faculty|points|hours";
    private static String campsHeader = "campid|campname|date|closingdate|usergroup|location|slots|ccslots|description|staffid";
    private static String suggestionsHeader = "campid|userid|content|status|timestamp";
    private static String enquiriesHeader = "enquiryid|campid|userid|content|position|timestamp";
    private static String enquiriesRepliesHeader = "enquiryid|userid|position|content|upvotes|timestamp";
    private static String campCommitteesHeader = "campid|userids";

    public static ArrayList<String[]> getCredentials(String fn){
        //String rex = "\\s*([A-Za-z]+)\\s*([A-Za-z0-9_@.]+)[;]*\\s*([A-Za-z]+)";
        String rex = "\\s*([A-Za-z]+)[|]+([A-Za-z0-9_@.]+)[;]*[|]+([A-Za-z]+)";
        return readFromFile(fn);
    }

    public static void setCredentials(String fn,ArrayList<String[]> al){
        writeToFile(fn,al,userListHeader);
    }

    private static boolean writeToFile(String fn, ArrayList<String[]> al, String header){
         try {
            FileWriter writer = new FileWriter(fn, false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(header);
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
    
    private static ArrayList<String[]> readFromFile(String fn){
        ArrayList<String[]> details = new ArrayList<String[]>();
         try {
            FileReader reader = new FileReader(fn);
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String line;
            int count = 0;

            //Pattern pat = Pattern.compile(rex, Pattern.CASE_INSENSITIVE);
            //Pattern headerPat = Pattern.compile(headerRegExp, Pattern.CASE_INSENSITIVE);
            
            while ((line = bufferedReader.readLine()) != null) {
                if(count==0) {
                    count++;
                    continue;
                }
                //Matcher mat = pat.matcher(line);
                String [] temp = line.split("\\|");
                details.add(temp);
            }
            reader.close();
            return details;
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return details;
    }
}
