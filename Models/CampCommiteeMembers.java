package Models;

import Utils.DatabaseUtils;

import java.util.ArrayList;

/**
 * This class is a list of all the Camp Committee Members
 */
public class CampCommiteeMembers {
    private ArrayList<CampCommiteeMember> ccmList = new ArrayList<>();
    private String campid;

    /**
     * The constructor of the list
     * @param campid is the ID of the camp
     */
    public CampCommiteeMembers(String campid){
        this.campid = campid;
        retrieveCCMFromDatabase();
    }

    /**
     * Retrieve the Camp Committee Members from the Database
     */
    private void retrieveCCMFromDatabase(){
        ArrayList<String []> users = DatabaseUtils.getAttendeesByCampID(campid);
        for(int j=0;j<users.size();j++){
            String [] userdata = DatabaseUtils.getUserByID(users.get(j)[1]);
            
            if(users.get(j)[2].equals("1")){
                ccmList.add(new CampCommiteeMember(users.get(j)[1],userdata[5]));
            }

        }
}

    /**
     * Convert the Camp Committee Members list into an array of strings
     * @return an array list of string arrays
     */
    private ArrayList<String[]> convertCCMToStringArr(){
        ArrayList<String[]> x = new ArrayList<>();
        for(int v=0;v<ccmList.size();v++){
            
            String[] p = {
                this.campid,
                    ccmList.get(v).getUserID(),
                "1"
            };
            x.add(p);
        }
        return x;
    }

    /**
     * This method is adding the Camp Committee Member into the list
     * @param x is the array list of string arrays
     * @returns whether the Camp Committee Member is added successfully or not.
     */
    public boolean addCampCommitteeMember(CampCommiteeMember x){
        boolean addres = this.ccmList.add(x);
        DatabaseUtils.setAttendeesByCampID(convertCCMToStringArr(),campid);
        return addres;
    }

    /**
     * Getter method for the number of Camp Committee Members
     * @return the number or the size of the members
     */
    public int getCampCommitteeCount(){
        return this.ccmList.size();
    }

    /**
     * Getter method for the list of Camp Committee Members
     * @return the list of Camp Committee Members
     */
    public ArrayList<CampCommiteeMember> getCampCommiteeMembers(){
        return this.ccmList;
    }
}
