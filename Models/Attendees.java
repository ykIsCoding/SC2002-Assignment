package Models;

import java.util.ArrayList;

import Utils.DatabaseUtils;

public class Attendees {
    ArrayList<Student> attendeeList = new ArrayList();
    String campid;

    public Attendees(String campid){
        this.campid = campid;
        retrieveAttendeesFromDatabase();
    }

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

    public boolean addAttendee(Student x){
        boolean addres = this.attendeeList.add(x);
        DatabaseUtils.setAttendeesByCampID(convertAttendeesToStringArr(),campid);
        return addres;
    }
    public boolean removeAttendee(Student x){
        boolean delRes = this.attendeeList.removeIf((Student z)->z.getUserID().equals(x.getUserID()));
        DatabaseUtils.setAttendeesByCampID(convertAttendeesToStringArr(),campid);
        return delRes;
    }

    public boolean isAttendee(Student x){
        for(Student v: attendeeList){
            if(v.getUserID().equals(x.getUserID())){
                return true;
            }
        }
        return false;
    }

    public int getAttendeeCount(){
        return this.attendeeList.size();
    }
}
