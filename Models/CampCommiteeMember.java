package Models;

public class CampCommiteeMember extends Student{
    private Camp campCommiteeCamps;
    private int accumulatedPoints;

    public CampCommiteeMember(String UserID, String Faculty) {
        super(UserID, Faculty);
    }

    public int getAccumulatedPoints() {
        return accumulatedPoints;
    }

    
}
