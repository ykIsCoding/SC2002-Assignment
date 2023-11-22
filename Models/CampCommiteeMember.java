package Models;

import Utils.DatabaseUtils;

public class CampCommiteeMember extends Student{
    //private Camp campCommiteeCamps;
    private int accumulatedPoints;

    public CampCommiteeMember(String UserID, String Faculty) {
        super(UserID, Faculty);
        this.accumulatedPoints = DatabaseUtils.getPoint(UserID);
    }

    public void addPoints(int pt){
        this.accumulatedPoints+=pt;
        DatabaseUtils.addPoint(pt, getUserID());
    }

    public int getAccumulatedPoints(){
        return this.accumulatedPoints;
    }


}
