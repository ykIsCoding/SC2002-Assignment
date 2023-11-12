package Models;

import Models.Abstract.AUser;

public class Staff extends AUser{
    private int points;
    private Camp createdCamps;

    public Staff(String UserID, String Faculty) {
        super(UserID, "Staff", Faculty);
    }

    @Override
    public void changePassword() {

    }

    // Getter and Setter for points
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    // Getter and Setter for createdCamps
    public Camp getCreatedCamps() {
        return createdCamps;
    }

    public void setCreatedCamps(Camp createdCamps) {
        this.createdCamps = createdCamps;
    }
}
