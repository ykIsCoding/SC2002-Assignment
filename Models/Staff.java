package Models;

import Models.Abstract.AUser;

import java.util.EnumSet;

/**
 * Staff class, inherited from AUser
 */
public class Staff extends AUser{
    private int points;
    private CampList createdCamps;
    

    /**
     * Staff constructor takes in user id and faculty
     * @param UserID user id of staff
     * @param Faculty faculty of staff
     */
    public Staff(String UserID, String Faculty) {
        super(UserID, "Staff", Faculty);
    }

}
