package Models;

import Utils.DatabaseUtils;

import java.util.ArrayList;

public class CampCommiteeMembers {
    private ArrayList<CampCommiteeMember> ccmList = new ArrayList<>();
    private String campid;

    public CampCommiteeMembers(String campid){
        this.campid = campid;
        retrieveCCMFromDatabase();
    }

    private void retrieveCCMFromDatabase(){
        ArrayList<String []> users = DatabaseUtils.getAttendeesByCampID(campid);
        for(int j=0;j<users.size();j++){
            String [] userdata = DatabaseUtils.getUserByID(users.get(j)[1]);
            
            if(users.get(j)[2].equals("1")){
                ccmList.add(new CampCommiteeMember(users.get(j)[1],userdata[5]));
            }

        }
}

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

    public boolean addCampCommitteeMember(CampCommiteeMember x){
        boolean addres = this.ccmList.add(x);
        DatabaseUtils.setAttendeesByCampID(convertCCMToStringArr(),campid);
        return addres;
    }

    public int getCampCommitteeCount(){
        return this.ccmList.size();
    }

    public ArrayList<CampCommiteeMember> getCampCommiteeMembers(){
        return this.ccmList;
    }
}
