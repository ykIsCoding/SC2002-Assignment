package Models;

import java.util.EnumSet;

import Utils.DatabaseUtils;

public class CampCommiteeMember extends Student{
    //private Camp campCommiteeCamps;
    private int accumulatedPoints;

    public CampCommiteeMember(String UserID, String Faculty) {
        super(UserID, Faculty);
    }

    public void addPoints(int pt){
        this.accumulatedPoints+=pt;
        DatabaseUtils.addPoint(pt, getUserID());
    }


}
