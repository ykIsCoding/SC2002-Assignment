package Utils;

import Models.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * The DatabaseUtils contains the utility functions for reading and writing data to the database
 * The functions for hashing and checking passwords are also in this class.
 */
public class DatabaseUtils {
   // private static final String headerRegExp = ".*\\|USERID\\|.*";
    private static final String userListHeader = "userid|name|email|username|password|faculty|points|hours|salt|firsttime";
    private static final String campsHeader = "campid|campname|date|closingdate|usergroup|location|slots|ccslots|description|staffid|visibility";
    private static final String suggestionsHeader = "campid|userid|status|suggestionID|campname|date|closingdate|usergroup|location|slots|ccslots|description|staffid";
    private static final String enquiriesHeader = "enquiryid|campid|userid|content|position|timestamp";
    private static final String enquiriesRepliesHeader = "enquiryid|userid|position|content|upvotes|timestamp|enquiryResponseID";
    private static final String campCommitteesHeader = "campid|userids";
    private static final String attendeesHeader = "campid|userids|campcommittee";

    /**
     * this function takes the file name and returns an arraylist of data
     * @param fn is the name of the file
     * @return an array list of array of strings
     */
    public static ArrayList<String[]> getCredentials(String fn){
        String rex = "\\s*([A-Za-z]+)[|]+([A-Za-z0-9_@.]+)[;]*[|]+([A-Za-z]+)";
        return readFromFile(fn);
    }

    /**
     * this function hashes the password set by the user using SHA512 for encryption. The function takes in the password and the salt to hash the password.
     * @param pw the password entered by the user
     * @param s the salt to hash the password
     * @return
     */
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

    /**
     * this function generates the salt for hashing the password
     * @return
     */
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

    /**
     * this function checks if the password entered by the user is correct
     * @param inputPW the password entered by the user
     * @param s1 the salt generated for the password entered by the user to has the inputPW
     * @param actualPW the correct password
     * @return
     */
    public static boolean checkPassword(String inputPW, String s1, String actualPW){
       
        return hashPassword(inputPW,s1).equals(actualPW);
    }


    /**
     * this function checks if a student is a camp committee member
     * @param studentID is the id of the student
     * @param campID is the id of the camp
     * @return true if student is camp committee member, else false.
     */
    public static boolean checkIfStudentIsCampCommitteeMember(String studentID, String campID){
         ArrayList<String[]> x = readFromFile("Data/attendees.txt");
         for(int c=0;c<x.size();c++){
                if(x.get(c)[0].equals(campID) && x.get(c)[1].equals(studentID) && x.get(c)[2].equals("1") ) return true;
         }
         return false;
    }

    /**
     * function checks if student already is in a camp committee
     * @param studentID the id of the student
     * @return true if the student already has a committee, else false
     */
    public static boolean checkIfStudentHasCampCommittee(String studentID){
         ArrayList<String[]> x = readFromFile("Data/attendees.txt");
         for(int c=0;c<x.size();c++){
                if(x.get(c)[1].equals(studentID) && x.get(c)[2].equals("1") ) return true;
         }
         return false;
    }

    /**
     * function checks if user is logging in for the first time
     * @param userid the id of the student
     * @return true if the user is logging in for the first time, else false
     */
    public static boolean checkIfUserLogsInForFirstTime(String userid ){
        //1 is not first time, 0 is first time
        return getUserByID(userid)[9].equals("0");
    }


    /**
     * function gets the user details in an array of Strings by the user's email from database
     * @param email of the user
     * @return an array of Strings containing the user data
     */
    public static String[] getUserByEmail(String email){
        String[] temp = {};
        ArrayList<String[]> x = readFromFile("Data/staff_list.txt");
        for(int b=0;b<x.size();b++){
            if(x.get(b)[2].equalsIgnoreCase(email)){
                return x.get(b);
            }
        }

        ArrayList<String[]> y = readFromFile("Data/student_list.txt");
        for(int b=0;b<y.size();b++){
            if(y.get(b)[2].equalsIgnoreCase(email)){
                return y.get(b);
            }
        }
        return null;
    }

    /**
     * function gets the user details in an array of Strings by the user's email from database
     * @param id is the student id
     * @return an array of Strings containing the user data
     */
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

    /**
     * functions gets from database the camp's id and outputs an arraylist containing an array of strings of the syggestions details
     * @param campID is the campid whose suggestions we wants to get
     * @return arraylist of array of strings containing the suggestion details
     */
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

    /**
     * function gets deletes the camp from the database by the suggestionid
     * @param s the suggestion ID
     * @return true
     */
    public static boolean deleteSuggestionBySuggestionID(String s){
        ArrayList<String[]> x = readFromFile("Data/suggestions.txt");
            x.removeIf((String[] info)->info[3].equals(s));
        writeToFile("Data/suggestions.txt", x, suggestionsHeader);
        return true;
    }

    /**
     * function deletes the enquiry by the enquiry id from database
     * @param s enquiry id of the enquiry to delete
     * @return true
     */
    public static boolean deleteEnquiryByEnquiryID(String s){
        ArrayList<String[]> x = readFromFile("Data/enquiries.txt");
            x.removeIf((String[] info)->info[0].equals(s));
        writeToFile("Data/enquiries.txt", x, enquiriesHeader);
        return true;
    }

    /**
     * function writes the suggestion details to the database
     * @param sl the new suggestionlist to write to database
     * @param campID the campid is id of the camp whose suggestions to update
     * @return true if success, else false
     */
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

    /**
     * function writes the points of the user to the database
     * @param pt the point to write to datbase
     * @param userID the user id whose points we want to write
     * @return true if successful, else false
     */
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

    /**
     * reads the point of the user from database
     * @param userID of the user
     * @return the integer point of the user
     */
    public static int getPoint(String userID){
        ArrayList<String[]> temp = new ArrayList<>();
        ArrayList<String[]> ccms = readFromFile("Data/student_list.txt");
        for(int b=0;b<ccms.size();b++){
            if(ccms.get(b)[0].equals(userID)){
                return Integer.valueOf(ccms.get(b)[6]);
            }
        }
        return 0;
    }


    /**
     * exports the data from database to the file
     * @param fn the name of the file
     * @param content content to put into the exported file
     * @return true if success, else false
     */
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

    /**
     * reads enquiries from the database by campid
     * @param campID the id of the camp
     * @return arraylist of string arrays containing the enquiries data
     */
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


    /**
     * reads the enquiry replies of the camp by the enquiry id
     * @param enquiryID the string of the enquiry whose replies we want to retrieve
     * @return arraylist of string arrays containing details of the enquiry replies
     */
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

    /**
     * write the enquiries replies to the database by the enquiryid
     * @param erl the arraylist of enquiry responses
     * @param enquiryID the enquiry id of the enquiry whose replies we want to write to
     * @return true if written to database, else false
     */
    public static boolean setEnquiriesRepliesByEnquiryID(ArrayList<EnquiryResponse> erl,String enquiryID){
           ArrayList<String[]> x = readFromFile("Data/enquiries_replies.txt");
            x.removeIf((String[] info)->info[0].equals(enquiryID));
            for(int b=0;b<erl.size();b++){
            String[] tmp = {
                    erl.get(b).getEnquiryID(),
                    erl.get(b).getUserID(),
                    erl.get(b).getPosiiton(),
                    erl.get(b).getContent(),
                String.valueOf(erl.get(b).getUpvotes()),
                    erl.get(b).getTimestamp(),
                    erl.get(b).getEnquiryResponseID(),
            };
            x.add(tmp);
        }
        
        writeToFile("Data/enquiries_replies.txt", x, enquiriesRepliesHeader);
        return true;
    }

    /**
     * write to the database the enquiries of the camp
     * @param al the enquiry list class
     */
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

    /**
     * writes the details of the user to the database
     * @param id the id of the user
     * @param nd the array of strings containing the details of the user
     * @return
     */
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


    /**
     * write the camp details to database
     * @param al arraylist containing array of strings with details of the camp
     * @return true if written , else false
     */
    public static boolean writeCamps(ArrayList<String[]> al){
        return writeToFile("Data/camps.txt", al, campsHeader);
    }

    /**
     * updates the camp details to database
     * @param al arraylist containing array of strings with details of the camp
     * @return true if written , else false
     */
    public static boolean updateCamps(ArrayList<String[]> al){
        return writeCamps(updateAL("Data/camps.txt",0,al));
    }

    /**
     * delete the camp from the database
     * @param al arraylist containing array of strings with details of the camp
     * @return true if deleted, else false
     */
    public static boolean deleteCamp(ArrayList<String[]> al){
        return deleteFromFile("Data/camps.txt",al,0,campsHeader);
    }

    /**
     * read the camp details from the database
     * @return arraylist of array of strings
     */
    public static ArrayList<String[]> readCamps(){
        return readFromFile("Data/camps.txt");
    }

    /**
     * write enquiries to database
     * @param al arraylist of array of strings containing details of the enquiry
     */
    public static void writeEnquiries(ArrayList<String[]> al){
        writeToFile("Data/enquiries.txt", al,enquiriesHeader);
    }

    /**
     * general purpose function to modify arraylist of array of strings based on column value of the database
     * @param fn filename whose data we want to modify
     * @param colNum column number to determine which array to change
     * @param al arraylist of strings of array containing the updated data
     * @return
     */
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

    /**
     * delete from database based on column value
     * @param fn filename whose data we want to modify
     * @param al arraylist of array of strings whose data we want to delete from database
     * @param colNum column number to determine which array to change
     * @param header header of the database
     * @return
     */
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

    /**
     * read the attendees of the camp from database by the campid
     * @param campID the id of the camp whose attendees we want to get
     * @return arraylist of array of strings
     */
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

    /**
     * write the attendees of the camp from database by the campid
     * @param attendeelist  arraylist of array of strings with details of the attendees
     * @param campID the id of the camp whose attendees we want to set
     * @return true if written correctly, else false
     */
    public static boolean setAttendeesByCampID(ArrayList<String[]> attendeelist, String campID){
        
        ArrayList<String[]> x = readFromFile("Data/attendees.txt");
        x.removeIf((String[] info)->info[0].equals(campID));
        for(int b=0;b<attendeelist.size();b++){
            String[] tmp = {
                campID,
                    attendeelist.get(b)[1],
                String.valueOf(attendeelist.get(b)[2])
            };
            x.add(tmp);
            
        }
        
        writeToFile("Data/attendees.txt", x, attendeesHeader);
        return true;
    }


    /**
     * write the data to file
     * @param fn file name of the database to write to
     * @param al arraylist of array of strings to write to database
     * @param header header for the file to set when writing to database
     * @return true if written successfully, else false
     */
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

    /**
     * read the data to file
     * @param fn file name of the database to read from
     * @return true if read successfully, else false
     */
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
