package Models;

import Models.Abstract.AUser;

import java.util.EnumSet;

import Models.PermissionList;

public class Staff extends AUser{
    private int points;
    private CampList createdCamps;
    private final EnumSet permissions = PermissionList.getStaffPermission();

    public Staff(String UserID, String Faculty) {
        super(UserID, "Staff", Faculty);
    }



    // Getter and Setter for points
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    // Getter and Setter for createdCamps
    public CampList getCreatedCamps() {
        return createdCamps;
    }

    public void setCreatedCamps(CampList createdCamps) {
        this.createdCamps = createdCamps;
    }
}
