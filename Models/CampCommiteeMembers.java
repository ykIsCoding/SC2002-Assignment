package Models;

import java.util.ArrayList;

import Utils.DatabaseUtils;

public class CampCommiteeMembers {
    ArrayList<CampCommiteeMember> ccmList = new ArrayList<>();
    String campid;

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
    public boolean removeCampComitteeMember(CampCommiteeMember x){
        boolean delRes = this.ccmList.removeIf((CampCommiteeMember z)->z.getUserID().equals(x.getUserID()));
        DatabaseUtils.setAttendeesByCampID(convertCCMToStringArr(),campid);
        return delRes;
    }

    public boolean isCampCommitteeMember(CampCommiteeMember x){
        for(CampCommiteeMember v: ccmList){
            if(v.getUserID().equals(x.getUserID())){
                return true;
            }
        }
        return false;
    }

    public int getCampCommitteeCount(){
        return this.ccmList.size();
    }
}
