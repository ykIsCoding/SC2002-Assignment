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

import Models.Enquiry;
import Models.EnquiryList;
import Models.EnquiryResponse;
import Models.Suggestion;
import Models.SuggestionList;

public class DatabaseUtils {
   // private static final String headerRegExp = ".*\\|USERID\\|.*";
    private static final String userListHeader = "userid|name|email|username|password|faculty|points|hours|salt";
    private static final String campsHeader = "campid|campname|date|closingdate|usergroup|location|slots|ccslots|description|staffid|visibility";
    private static final String suggestionsHeader = "campid|userid|status|suggestionID|campname|date|closingdate|usergroup|location|slots|ccslots|description|staffid";
    private static final String enquiriesHeader = "enquiryid|campid|userid|content|position|timestamp";
    private static final String enquiriesRepliesHeader = "enquiryid|userid|position|content|upvotes|timestamp|enquiryResponseID";
    private static final String campCommitteesHeader = "campid|userids";
    private static final String attendeesHeader = "campid|userids|campcommittee";

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



    public static boolean checkIfStudentIsCampCommitteeMember(String studentID, String campID){
         ArrayList<String[]> x = readFromFile("Data/attendees.txt");
         for(int c=0;c<x.size();c++){
                if(x.get(c)[0].equals(campID) && x.get(c)[1].equals(studentID) && x.get(c)[2].equals("1") ) return true;
         }
         return false;
    }

    public static boolean checkIfStudentHasCampCommittee(String studentID){
         ArrayList<String[]> x = readFromFile("Data/attendees.txt");
         for(int c=0;c<x.size();c++){
                if(x.get(c)[1].equals(studentID) && x.get(c)[2].equals("1") ) return true;
         }
         return false;
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

    public static String[] getUserByID(String id){
        String[] temp = {};
        ArrayList<String[]> x = readFromFile("Data/staff_list.txt");
        for(int b=0;b<x.size();b++){
            if(x.get(b)[0].equals(id)){
                return x.get(b);
            }
        }

        ArrayList<String[]> y = readFromFile("Data/student_list.txt");
        for(int b=0;b<y.size();b++){
            if(y.get(b)[0].equals(id)){
                return y.get(b);
            }
        }
        return null;
    }

    public static ArrayList<String[]> getSuggestionsByCampID(String campID){
        ArrayList<String[]> temp = new ArrayList<>();
        ArrayList<String[]> x = readFromFile("Data/suggestions.txt");
        for(int b=0;b<x.size();b++){
            if(x.get(b)[0].equals(campID)){
                temp.add(x.get(b));
            }
        }
        return temp;
    }

    public static boolean deleteSuggestionBySuggestionID(String s){
        ArrayList<String[]> x = readFromFile("Data/suggestions.txt");
            x.removeIf((String[] info)->info[3].equals(s));
        writeToFile("Data/suggestions.txt", x, suggestionsHeader);
        return true;
    }

    public static boolean deleteEnquiryByEnquiryID(String s){
        ArrayList<String[]> x = readFromFile("Data/enquiries.txt");
            x.removeIf((String[] info)->info[0].equals(s));
        writeToFile("Data/enquiries.txt", x, enquiriesHeader);
        return true;
    }

    public static boolean setSuggestionsByCampID(SuggestionList sl, String campID){
                    try{
        ArrayList<Suggestion> sls = sl.getSuggestionList();
        
        ArrayList<String[]> temp = new ArrayList<>();
        ArrayList<String[]> x = readFromFile("Data/suggestions.txt");
        for(int b=0;b<x.size();b++){
            for(int n=0;n<sls.size();n++){
                if(x.get(b)[3].equals(sls.get(n).getSuggestionID())){
                    String[] tmp = {
                sls.get(n).getSuggestionCamp().getCampID(),
                sls.get(n).getUserID(),
                sls.get(n).getStatus(),
                sls.get(n).getSuggestionID(),
                sls.get(n).getSuggestionCamp().getCampName(),
                PageUtils.localDateToString(sls.get(n).getSuggestionCamp().getDate()),
                PageUtils.localDateToString(sls.get(n).getSuggestionCamp().getRegistrationClosingDate()),
                sls.get(n).getSuggestionCamp().getUserGroup(),
                sls.get(n).getSuggestionCamp().getLocation(),
                String.valueOf(sls.get(n).getSuggestionCamp().getTotalSlots()),
                String.valueOf(sls.get(n).getSuggestionCamp().getCampCommitteeSlots()),
                sls.get(n).getSuggestionCamp().getDescription(),
                sls.get(n).getSuggestionCamp().getStaffInCharge()
            };
                    x.set(b, tmp);
                    sls.remove(sls.get(n));
                }
            }
            temp.add(x.get(b));
            
        }
        if(sls.size()>0){
            
            for(int o=0;o<sls.size();o++){
                String[] tmp = {
                         sls.get(o).getSuggestionCamp().getCampID(),
                sls.get(o).getUserID(),
                sls.get(o).getStatus(),
                sls.get(o).getSuggestionID(),
                sls.get(o).getSuggestionCamp().getCampName(),
                PageUtils.localDateToString(sls.get(o).getSuggestionCamp().getDate()),
                PageUtils.localDateToString(sls.get(o).getSuggestionCamp().getRegistrationClosingDate()),
                sls.get(o).getSuggestionCamp().getUserGroup(),
                sls.get(o).getSuggestionCamp().getLocation(),
                String.valueOf(sls.get(o).getSuggestionCamp().getTotalSlots()),
                String.valueOf(sls.get(o).getSuggestionCamp().getCampCommitteeSlots()),
                sls.get(o).getSuggestionCamp().getDescription(),
                sls.get(o).getSuggestionCamp().getStaffInCharge()
                    };
                    temp.add(tmp);
            }
        }
        writeToFile("Data/suggestions.txt", temp, suggestionsHeader);
        return true;
    }catch(Exception e){
            System.out.println(e);
        }
    return false;
}

    public static boolean addPoint(int pt, String userID){
        ArrayList<String[]> temp = new ArrayList<>();
        ArrayList<String[]> ccms = readFromFile("Data/student_list.txt");
        for(int b=0;b<ccms.size();b++){
            if(ccms.get(b)[0].equals(userID)){
                int ns = Integer.valueOf(ccms.get(b)[6])+pt;
                ccms.get(b)[6]=Integer.toString(ns);
                writeToFile("Data/student_list.txt", ccms, userListHeader);
                return true;
            }
        }
        return false;
    }



    public static boolean exportFile(String fn, String content){
               try {
            FileWriter writer = new FileWriter(fn, false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(content);
            bufferedWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<String[]> getEnquiriesByCampID(String campID){
        ArrayList<String[]> temp = new ArrayList<>();
        ArrayList<String[]> x = readFromFile("Data/enquiries.txt");
        for(int b=0;b<x.size();b++){
            if(x.get(b)[1].equals(campID)){
                temp.add(x.get(b));
            }
        }
        return temp;
    }



    public static ArrayList<String[]> getEnquiriesRepliesByEnquiryID(String enquiryID){
        ArrayList<String[]> temp = new ArrayList<>();
        ArrayList<String[]> x = readFromFile("Data/enquiries_replies.txt");
        for(int b=0;b<x.size();b++){
            if(x.get(b)[0].equals(enquiryID)){
                temp.add(x.get(b));
            }
        }
        return temp;
    }

    public static boolean setEnquiriesRepliesByEnquiryID(ArrayList<EnquiryResponse> erl,String enquiryID){
           ArrayList<String[]> x = readFromFile("Data/enquiries_replies.txt");
            x.removeIf((String[] info)->info[0].equals(enquiryID));
            for(int b=0;b<erl.size();b++){
            String[] tmp = {
                erl.get(b).getEnquiryID().toString(),
                erl.get(b).getUserID().toString(),
                erl.get(b).getPosiiton().toString(),
                erl.get(b).getContent().toString(),
                String.valueOf(erl.get(b).getUpvotes()),
                erl.get(b).getTimestamp().toString(),
                erl.get(b).getEnquiryResponseID().toString(),
            };
            x.add(tmp);
        }
        
        writeToFile("Data/enquiries_replies.txt", x, enquiriesRepliesHeader);
        return true;
    }

    public static void setEnquiriesByCampID(EnquiryList al){
        try{
        ArrayList<Enquiry> k = al.getEnquiryList();
        ArrayList<String[]> temp = new ArrayList<>();
        ArrayList<String[]> x = readFromFile("Data/enquiries.txt");
        for(int b=0;b<x.size();b++){
            for(int n=0;n<al.getEnquiryList().size();n++){
                if(x.get(b)[0].equals(k.get(n).getEnquiryID())){
                    String[] tmpp = {
                        al.getEnquiryList().get(n).getEnquiryID(),
                        al.getEnquiryList().get(n).getCampID(),
                        al.getEnquiryList().get(n).getUserID(),
                        al.getEnquiryList().get(n).getContent(),
                        getUserByID(al.getEnquiryList().get(n).getUserID())[0],
                        al.getEnquiryList().get(n).getTimestamp()
                    };
                    x.set(b, tmpp);
                    k.remove(al.getEnquiryList().get(n));
                }
            }
            temp.add(x.get(b));
            
        }
        if(k.size()>0){
            for(int o=0;o<k.size();o++){
                String[] tmp = {
                        al.getEnquiryList().get(o).getEnquiryID(),
                        al.getEnquiryList().get(o).getCampID(),
                        al.getEnquiryList().get(o).getUserID(),
                        al.getEnquiryList().get(o).getContent(),
                        al.getEnquiryList().get(o).getTimestamp()
                    };
                    temp.add(tmp);
            }
        }
        writeEnquiries(temp);
    }catch(Exception e){
            System.out.println(e);
        }
    }

    public static boolean setUserByID(String id, String[] nd){
        ArrayList<String[]> ndd = new ArrayList<>();
        ArrayList<String[]> x = readFromFile("Data/staff_list.txt");
        for(int b=0;b<x.size();b++){
            if(x.get(b)[0].equals(id)){
                ndd.add(nd);
            }else{
                ndd.add(x.get(b));
            }
        }
        writeToFile("Data/staff_list.txt", ndd, userListHeader);
        ArrayList<String[]> ndds = new ArrayList<>();
        ArrayList<String[]> y = readFromFile("Data/student_list.txt");
        for(int b=0;b<y.size();b++){
            if(y.get(b)[0].equals(id)){
                ndds.add(nd);
            }else{
                ndds.add(y.get(b));
            }
        }
        writeToFile("Data/student_list.txt", ndds, userListHeader);
        return true;
    }



    public static boolean writeCamps(ArrayList<String[]> al){
        return writeToFile("Data/camps.txt", al, campsHeader);
    }

    public static boolean updateCamps(ArrayList<String[]> al){
        return writeCamps(updateAL("Data/camps.txt",0,al));
    }

    public static boolean deleteCamp(ArrayList<String[]> al){
        return deleteFromFile("Data/camps.txt",al,0,campsHeader);
    }




    public static ArrayList<String[]> readCamps(){
        return readFromFile("Data/camps.txt");
    }






    public static void writeEnquiries(ArrayList<String[]> al){
        writeToFile("Data/enquiries.txt", al,enquiriesHeader);
    }







    private static ArrayList<String[]> updateAL(String fn, int colNum, ArrayList<String[]> al){
        ArrayList<String[]> tmp = readFromFile(fn);
        for(int x=0;x<al.size();x++){
            for(int v=0;v<tmp.size();v++){
                if(tmp.get(v)[colNum].equals(al.get(x)[colNum])){
                    tmp.set(v,al.get(x));
                }
            }
        }
        return tmp;
    }

    private static boolean deleteFromFile(String fn, ArrayList<String[]> al,int colNum, String header){
        try{
        ArrayList<String[]> tmp = readFromFile(fn);
        for(int x=0;x<al.size();x++){
            for(int v=0;v<tmp.size();v++){
                if(tmp.get(v)[colNum].equals(al.get(x)[colNum])){
                    tmp.remove(tmp.get(v));
                }
            }
        }
        writeToFile(fn,tmp,header);
        return true;
    }catch(Exception e){
        return false;
    }
    }

    public static ArrayList<String[]> getAttendeesByCampID(String campID){
        ArrayList<String[]> temp = new ArrayList<>();
        ArrayList<String[]> x = readFromFile("Data/attendees.txt");
        for(int b=0;b<x.size();b++){
            if(x.get(b)[0].equals(campID)){
                temp.add(x.get(b));
            }
        }
        return temp;
    }

    public static boolean setAttendeesByCampID(ArrayList<String[]> attendeelist, String campID){
        
        ArrayList<String[]> x = readFromFile("Data/attendees.txt");
        x.removeIf((String[] info)->info[0].equals(campID));
        for(int b=0;b<attendeelist.size();b++){
            String[] tmp = {
                campID,
                attendeelist.get(b)[1].toString(),
                String.valueOf(attendeelist.get(b)[2])
            };
            x.add(tmp);
            
        }
        
        writeToFile("Data/attendees.txt", x, attendeesHeader);
        return true;
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
