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

}
