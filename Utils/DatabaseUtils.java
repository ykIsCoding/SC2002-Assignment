package Utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static String hashPassword(String pw,String s ){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(s.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(pw.getBytes(StandardCharsets.UTF_8));
            StringBuilder sbuilder = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sbuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sbuilder.toString();
        } catch (Exception e) {
            
        }
        return null;
    }

    public static byte[] generateS(){
        try{
            SecureRandom random = new SecureRandom();
            byte[] s = new byte[16];
            random.nextBytes(s);
            return s;
        }catch(Exception e){
            System.out.println("error");
        }
        return null;
    }

    public static boolean checkPassword(String inputPW, String s1, String actualPW){
        return hashPassword(inputPW,s1).equals(actualPW);
    }

    public static String[] getUserByEmail(String email){
        String[] temp = {};
        ArrayList<String[]> x = readFromFile("Data/staff_list.txt");
        for(int b=0;b<x.size();b++){
            if(x.get(b)[2].toLowerCase().equals(email.toLowerCase())){
                return x.get(b);
            }
        }

        ArrayList<String[]> y = readFromFile("Data/student_list.txt");
        for(int b=0;b<y.size();b++){
            if(y.get(b)[2].toLowerCase().equals(email.toLowerCase())){
                return y.get(b);
            }
        }
        return null;
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
