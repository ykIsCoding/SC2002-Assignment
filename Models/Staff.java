package Models;

import Models.Abstract.AUser;

import java.util.EnumSet;

public class Staff extends AUser{
    private int points;
    private CampList createdCamps;
    private final EnumSet permissions = PermissionList.getStaffPermission();

    public Staff(String UserID, String Faculty) {
        super(UserID, "Staff", Faculty);
    }

}
