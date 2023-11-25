package Models;

import Utils.DatabaseUtils;

import java.util.ArrayList;

/**
 * Attendees comprises the list of attendees for each camp
 */
public class Attendees {
    ArrayList<Student> attendeeList = new ArrayList();
    String campid;

    /**
     * constructor takes in the campid of the camp with the attendees
     * @param campid
     */
    public Attendees(String campid){
        this.campid = campid;
        retrieveAttendeesFromDatabase();
    }

    /**
     * retreieve the attendees from the database and sets the attendeelist
     */
    private void retrieveAttendeesFromDatabase(){
        ArrayList<Student> al = new ArrayList<>();
        ArrayList<String []> users = DatabaseUtils.getAttendeesByCampID(campid);
        for(int j=0;j<users.size();j++){
            
            String [] userdata = DatabaseUtils.getUserByID(users.get(j)[1]);
            if(!userdata.equals(null)){
                al.add(new Student(users.get(j)[1],userdata[5]));
            }

        }
        
        this.attendeeList = al;
}

    /**
     * convert the arraylist of attendee to an arraylist of array of strings.
     * @return arraylist of array of strings
     */
    private ArrayList<String[]> convertAttendeesToStringArr(){
        ArrayList<String[]> x = new ArrayList<>();
        for(int v=0;v<attendeeList.size();v++){
            String isCC = "0";
            if(DatabaseUtils.checkIfStudentIsCampCommitteeMember(attendeeList.get(v).getUserID(), campid)){
                isCC="1";
            }
            String[] p = {
                this.campid,
                    attendeeList.get(v).getUserID(),
                isCC
            };
            x.add(p);
        }
        return x;
    }

    /**
     * add attendee to the attendee list
     * @param x student to add to attendee list
     * @return true if add, else false
     */
    public boolean addAttendee(Student x){
        boolean addres = this.attendeeList.add(x);
        DatabaseUtils.setAttendeesByCampID(convertAttendeesToStringArr(),campid);
        return addres;
    }

    /**
     * remove attendee from the attendee list
     * @param x student to remove from the attendee list
     * @return true if removed, else false
     */
    public boolean removeAttendee(Student x){
        boolean delRes = this.attendeeList.removeIf((Student z)->z.getUserID().equals(x.getUserID()));
        DatabaseUtils.setAttendeesByCampID(convertAttendeesToStringArr(),campid);
        return delRes;
    }

    /**
     * checks if student is an attendee of the camp
     * @param x student to check for
     * @return true student is attendee, else false
     */
    public boolean isAttendee(Student x){
        for(Student v: attendeeList){
            if(v.getUserID().equals(x.getUserID())){
                return true;
            }
        }
        return false;
    }

    /**
     * gets the number of attendees in the attendeelist
     * @return the integer of the number of attendees
     */
    public int getAttendeeCount(){
        return this.attendeeList.size();
    }
}
