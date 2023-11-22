package Models;

import Utils.DatabaseUtils;

/**
 * Camp Committee Member is a subclass of the Student as the member "is a" student.
 * Hence, it inherits the Student parent class.
 */

public class CampCommiteeMember extends Student{
    //private Camp campCommiteeCamps;
    private int accumulatedPoints;

    /**
     * The constructors of the Camp Committee Member
     * @param UserID is the ID of the user
     * @param Faculty is the faculty of the user, such as SCSE, NBS etc
     */
    public CampCommiteeMember(String UserID, String Faculty) {
        super(UserID, Faculty);
        this.accumulatedPoints = DatabaseUtils.getPoint(UserID);
    }

    /**
     * Method to add points for the camp committee member
     * @param pt is the points that they would be adding
     */
    public void addPoints(int pt){
        this.accumulatedPoints+=pt;
        DatabaseUtils.addPoint(pt, getUserID());
    }

    /**
     * Getter method for the accumulated points the camp committee member has
     * @return the total points the member has
     */
    public int getAccumulatedPoints(){
        return this.accumulatedPoints;
    }


}
